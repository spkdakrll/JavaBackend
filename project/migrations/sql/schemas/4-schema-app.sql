CREATE TABLE IF NOT EXISTS chat_link
(
    chat_id BIGINT REFERENCES chats(id) NOT NULL,
    link_id BIGINT REFERENCES links(id) NOT NULL,
    PRIMARY KEY (chat_id, link_id)
);