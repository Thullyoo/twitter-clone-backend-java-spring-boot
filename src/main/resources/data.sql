INSERT INTO TB_ROLES (id, name) VALUES (0, 'common')
ON CONFLICT (id) DO NOTHING;

INSERT INTO TB_ROLES (id, name) VALUES (1, 'admin')
ON CONFLICT (id) DO NOTHING;