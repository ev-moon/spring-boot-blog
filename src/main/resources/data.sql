INSERT INTO USERS (id, userid, password, name, email)
VALUES (1, 'user1', 'user1', 'user1', 'user1@abc.com');
INSERT INTO USERS (id, userid, password, name, email)
VALUES (2, 'user2', 'user2', 'user2', 'user2@ab.com');

INSERT INTO ARTICLES (id, author_id, title, content)
VALUES (1, 1, 'test title', 'test content');

INSERT INTO REPLIES (id, author_id, article_id, content)
VALUES (1, 1, 1, 'test reply');
