INSERT INTO article(title, content) VALUES ('가가가가','111');
INSERT INTO article(title, content) VALUES ('가가가가','222');
INSERT INTO article(title, content) VALUES ('가가가가','333');

INSERT INTO article(title, content) VALUES ('당신의 인생 영화는?','댓글 ㄱ');
INSERT INTO article(title, content) VALUES ('당신의 소울푸드는?','댓글 ㄱ');
INSERT INTO article(title, content) VALUES ('당신의 취미는?','댓글 ㄱ');

INSERT INTO comment(id, article_id, nickname, body) VALUES(1,4,'Park','너의 결혼식');
INSERT INTO comment(id, article_id, nickname, body) VALUES(2,4,'Kim','스타워즈');
INSERT INTO comment(id, article_id, nickname, body) VALUES(3,4,'Choi','세 얼간이');

INSERT INTO comment(id, article_id, nickname, body) VALUES(4,5,'Park','치킨');
INSERT INTO comment(id, article_id, nickname, body) VALUES(5,5,'Kim','양념게장');
INSERT INTO comment(id, article_id, nickname, body) VALUES(6,5,'Choi','닭갈비');

INSERT INTO comment(id, article_id, nickname, body) VALUES(7,6,'Park','유튜브');
INSERT INTO comment(id, article_id, nickname, body) VALUES(8,6,'Kim','넷플릭스');
INSERT INTO comment(id, article_id, nickname, body) VALUES(9,6,'Choi','테니스');