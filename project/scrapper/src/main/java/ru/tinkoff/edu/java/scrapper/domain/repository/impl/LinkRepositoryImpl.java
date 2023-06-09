package ru.tinkoff.edu.java.scrapper.domain.repository.impl;

import lombok.RequiredArgsConstructor;
import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.BadEntityException;
import ru.tinkoff.edu.java.scrapper.domain.entities.LinkData;
import ru.tinkoff.edu.java.scrapper.domain.entities.ConvertorFromMapToJson;
import ru.tinkoff.edu.java.scrapper.domain.repository.interfaces.LinkRepository;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class LinkRepositoryImpl implements LinkRepository {
    private final JdbcTemplate template;
    private final RowMapper<LinkData> rowMapper = (rs, rowNum) -> {
        LinkData linkDataBD = new LinkData();
        linkDataBD.setId(rs.getLong(1));
        linkDataBD.setLink(rs.getString(2));
        linkDataBD.setDomainId(rs.getLong(3));
        linkDataBD.setDataChanges(
                new ConvertorFromMapToJson()
                        .convertToEntityAttribute((PGobject) rs.getObject(4))
        );
        linkDataBD.setPageUpdateDate(
                new Date(rs.getDate(5).getTime()).toInstant().atOffset(ZoneOffset.UTC)
        );
        return linkDataBD;
    };

    private static final String INSERT_QUERY = "INSERT INTO links(link, domain_id, page_updated_date, data_changes) VALUES (?, ?, ?, ?)";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM links WHERE id=?";
    private static final String DELETE_BY_LINK_QUERY = "DELETE FROM links WHERE link=?";
    private static final String UPDATE_LATEST_SCHEDULER_DATE_QUERY = "UPDATE links SET scheduler_updated_date = now() WHERE id = ?";
    private static final String SELECT_BY_LINK_QUERY = "SELECT id, link, domain_id, data_changes, page_updated_date FROM links WHERE link=?";
    private static final String SELECT_ALL_QUERY = "SELECT id, link, domain_id, data_changes, page_updated_date FROM links ORDER BY %s %s %s";
    private static final String SELECT_BY_MANY_LINK_ID_QUERY = "SELECT id, link, domain_id, data_changes, page_updated_date FROM links WHERE id IN (%s)";
    private static final String UPDATE_DATA_CHANGES_QUERY = "UPDATE links SET data_changes=?, page_updated_date=? where id=?";


    void checkEntity(LinkData linkData) throws BadEntityException {
        if (linkData == null || linkData.getLink() == null
                || linkData.getDataChanges() == null || linkData.getDomainId() == null)
            throw new BadEntityException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void add(LinkData link) throws BadEntityException {
        checkEntity(link);
        template.update(
                INSERT_QUERY,
                link.getLink(),
                link.getDomainId(),
                link.getPageUpdateDate(),
                new ConvertorFromMapToJson().convertToDatabaseColumn(link.getDataChanges())
        );
    }

    @Override
    public void remove(long id) {
        template.update(DELETE_BY_ID_QUERY, id);
    }

    @Override
    public void remove(String link) {
        template.update(DELETE_BY_LINK_QUERY, link);
    }

    @Override
    public void updateUpdatedDateLink(long linkId) {
        template.update(UPDATE_LATEST_SCHEDULER_DATE_QUERY, linkId);
    }

    @Override
    public void updateDataChangesLink(Map<String, String> dataChanges, Long linkId) {
        String dateUpdate = dataChanges.get("updated_date");
        dataChanges.remove("updated_date");
        template.update(
                UPDATE_DATA_CHANGES_QUERY,
                new ConvertorFromMapToJson().convertToDatabaseColumn(dataChanges),
                OffsetDateTime.parse(dateUpdate),
//                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateUpdate),
                linkId
        );
    }

    public LinkData getByLink(String link) {
        return template.queryForObject(SELECT_BY_LINK_QUERY, rowMapper, link);
    }

    @Override
    public List<LinkData> findAll(String nameField, boolean orderASC, Integer limit) {
        return template.query(
                String.format(
                        SELECT_ALL_QUERY,
                        nameField.isEmpty() ? "id" : nameField,
                        orderASC ? "" : "DESC",
                        limit == null ? "" : String.format("LIMIT %d", limit)
                ),
                rowMapper
        );
    }

    @Override
    public List<LinkData> getByLinkIds(List<Long> arrChatLink) {
        String inSql = String.join(",", Collections.nCopies(arrChatLink.size(), "?" ));
        return template.query(
                String.format(SELECT_BY_MANY_LINK_ID_QUERY, inSql),
                rowMapper,
                arrChatLink.toArray()
        );
    }
}
