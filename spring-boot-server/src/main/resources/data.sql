-- Inserção dos tipos de usuário
INSERT INTO TIPO_USUARIO (origem, descr) VALUES ( 'M', 'Magistrado');
INSERT INTO TIPO_USUARIO (origem, descr) VALUES ( 'F', 'Funcionário');
INSERT INTO TIPO_USUARIO (origem, descr) VALUES ( 'T', 'Terceirizado');
INSERT INTO TIPO_USUARIO (origem, descr) VALUES ( 'A', 'Aposentado');
INSERT INTO TIPO_USUARIO (origem, descr) VALUES ( 'P', 'Pensionista');
INSERT INTO TIPO_USUARIO (origem, descr) VALUES ( 'C', 'Cotista');
INSERT INTO TIPO_USUARIO (origem, descr) VALUES ( 'E', 'Externo');

-- Inserção dos usuárioS
INSERT INTO USUARIO (nome_usu, matr_usu, data_nasc, email, origem) VALUES ( 'VIVIANE GOES DELZI', 'RJ162042', '2025-01-13', 'testenegocio@hotmail.com', 'E');
INSERT INTO USUARIO (nome_usu, matr_usu, data_nasc, email, origem) VALUES ( 'SABINE SYLVIA SCHMITT', 'RJ179264', '2025-01-13', 'teste@teste.com.br', 'E');
INSERT INTO USUARIO (nome_usu, matr_usu, data_nasc, email, origem) VALUES ( 'ALEXANDRE MARQUES PAULA', '30435', '2025-02-01', 'teste@tjrj.jus.br', 'F');
INSERT INTO USUARIO (nome_usu, matr_usu, data_nasc, email, origem) VALUES ( 'FERNANDO LUIZ DE ABREU NETTO', '6716', '2025-02-03', 'teste@tjrj.jus', 'A');
INSERT INTO USUARIO (nome_usu, matr_usu, data_nasc, email, origem) VALUES ( 'CLAUDIA MARIA MENEZES SOARES', '8041', '2025-01-13', 'testes@testando2.com.br', 'A');
INSERT INTO USUARIO (nome_usu, matr_usu, data_nasc, email, origem) VALUES ( 'PAULO DA SILVA XAVIER', 'RJ144977', '2014-09-14', 'paulodasilvaxavierteste@uol.com.br', 'E');
INSERT INTO USUARIO (nome_usu, matr_usu, data_nasc, email, origem) VALUES ( 'PAULA CRISTINA VASCONCELOS CAVALCANTE', 'RJ176594', '2019-11-17', 'adv.paulacavalcante_teste@yahoo.com.br', 'E');
INSERT INTO USUARIO (nome_usu, matr_usu, data_nasc, email, origem) VALUES ( 'JOSE PIMENTEL JUNIOR', '24798649791', '2025-01-13', 'teste@teste.com.br', 'P');
INSERT INTO USUARIO (nome_usu, matr_usu, data_nasc, email, origem) VALUES ( 'TESTE - PREPOSTO FEBRABAN', '000123', '2016-06-02', 'teste@teste.com.br', 'E');

DROP ALIAS IF EXISTS BUSCAR_USUARIOS;
CREATE ALIAS BUSCAR_USUARIOS AS '
import java.sql.*;
@CODE
ResultSet buscarOrigemPorProcedure(Connection conn, String origemFiltro) throws SQLException {
    String sql = "SELECT * FROM USUARIO";
    if (origemFiltro != null && !origemFiltro.isEmpty()) {
        sql += " WHERE ORIGEM = ''" + origemFiltro + "''";
    }
    return conn.createStatement().executeQuery(sql);
}';

DROP ALIAS IF EXISTS LISTAR_USUARIOS;
CREATE ALIAS LISTAR_USUARIOS AS '
import java.sql.*;
@CODE
ResultSet buscarUsuariosPorProcedure(Connection conn) throws SQLException {
    return conn.createStatement().executeQuery("SELECT * FROM USUARIO");
}';