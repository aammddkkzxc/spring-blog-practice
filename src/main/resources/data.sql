INSERT INTO article (title, content, created_at, updated_at)
VALUES ('제목1', '내용1', NOW(), NOW());
INSERT INTO article (title, content, created_at, updated_at)
VALUES ('제목2', '내용2', NOW(), NOW());
INSERT INTO article (title, content, created_at, updated_at)
VALUES ('제목3', '내용3', NOW(), NOW());

INSERT INTO users (email, password)
VALUES ('ss@sss', '$2a$12$gj9RPbLyLuHZm0QJSrPoGOnh9wZN/EH3JVevXdjOYhWkclxgs59la');

-- INSERT INTO comment (body, created_at) VALUES ('댓글 내용1', NOW());
-- INSERT INTO comment (body, created_at) VALUES ('댓글 내용2', NOW());
-- INSERT INTO comment (body, created_at) VALUES ('댓글 내용3', NOW());