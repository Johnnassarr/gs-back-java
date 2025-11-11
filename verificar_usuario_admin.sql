-- Script para verificar e corrigir usuários no banco de dados
-- Execute este script no pgAdmin4 para diagnosticar problemas de autenticação

-- 1. Verificar todos os usuários e suas roles
SELECT 
    id,
    username,
    email,
    role,
    CASE 
        WHEN role IS NULL THEN '⚠️ PROBLEMA: Role está NULL'
        WHEN role = 'ADMIN' THEN '✅ OK: É ADMIN'
        WHEN role = 'USER' THEN 'ℹ️ INFO: É USER (não pode criar/editar/deletar)'
        ELSE '⚠️ ATENÇÃO: Role desconhecida: ' || role
    END AS status
FROM usuario
ORDER BY id;

-- 2. Verificar se há usuários sem role (NULL)
SELECT 
    id,
    username,
    email,
    '⚠️ ATENÇÃO: Este usuário não tem role definida!' AS problema
FROM usuario
WHERE role IS NULL;

-- 3. Atualizar um usuário específico para ADMIN (substitua o email)
-- DESCOMENTE A LINHA ABAIXO E SUBSTITUA O EMAIL:
-- UPDATE usuario SET role = 'ADMIN' WHERE email = 'admin@example.com';

-- 4. Verificar quantos usuários são ADMIN
SELECT 
    COUNT(*) AS total_administradores
FROM usuario
WHERE role = 'ADMIN';

-- 5. Verificar quantos usuários são USER
SELECT 
    COUNT(*) AS total_usuarios
FROM usuario
WHERE role = 'USER';

-- 6. Verificar usuários sem role
SELECT 
    COUNT(*) AS usuarios_sem_role
FROM usuario
WHERE role IS NULL;

