package Persistencia;

import java.sql.SQLException;

public class BuildDeTabelas extends ConexaoComBancoDeDados {

    private final String tabelaPerfil = "CREATE TABLE IF NOT EXISTS PERFIL ("
            + "CODIGO INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY(START WITH 101, INCREMENT BY 1) ,"
            + "NOME VARCHAR(50) NOT NULL,"
            + "DESCRICAO VARCHAR(100) NOT NULL,"
            + "PRIMARY KEY(CODIGO));";

    private final String tabelaUsuario = "CREATE TABLE IF NOT EXISTS USUARIO ("
            + "ID INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY(START WITH 100, INCREMENT BY 1),"
            + "CPF VARCHAR(13) NOT NULL,"
            + "NOME VARCHAR(50) NOT NULL,"
            + "USERNAME VARCHAR(30) NOT NULL,"
            + "SENHA VARCHAR(100) NOT NULL,"
            + "CODIGO_PERFIL INTEGER NOT NULL,"
            + "TELEFONE VARCHAR(15) NOT NULL,"
            + "MATRICULA VARCHAR(5) NOT NULL,"
            + "DATA_NASCIMENTO DATE NOT NULL,"
            + "PRIMARY KEY(ID),"
            + "FOREIGN KEY(CODIGO_PERFIL) REFERENCES PERFIL(CODIGO));";

    private final String tabelaMedico = "CREATE TABLE IF NOT EXISTS MEDICO("
            + "CRM INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY(START WITH 122, INCREMENT BY 1),"
            + "NOME VARCHAR(80) NOT NULL, "
            + "ESPECIALIDADE VARCHAR(20) NOT NULL,"
            + "SEXO VARCHAR(1),"
            + "DATA_NASCIMENTO DATE,"
            + "SALARIO DECIMAL(7,2),"
            + "PRIMARY KEY(CRM) );";

    private final String tabelaPaciente = "CREATE TABLE IF NOT EXISTS PACIENTE("
            + "ID INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1),"
            + "CPF VARCHAR(13) NOT NULL,"
            + "NOME VARCHAR(80) NOT NULL, "
            + "TELEFONE VARCHAR(15) NOT NULL,"
            + "SEXO VARCHAR(1),"
            + "DATA_NASCIMENTO DATE,"
            + "ENDERECO VARCHAR(60) NOT NULL,"
            + "PRIMARY KEY(ID) );";

    private final String tabelaConsulta = "CREATE TABLE IF NOT EXISTS CONSULTA("
            + "ID INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1),"
            + "ID_PLANO INTEGER NOT NULL,"
            + "ID_PACIENTE INTEGER NOT NULL,"
            + "CRM_MEDICO INTEGER NOT NULL, "
            + "SALA VARCHAR(3) NOT NULL,"
            + "DATA_DA_CONSULTA DATE NOT NULL,"
            + "HORA_DA_CONSULTA DATE NOT NULL,"
            + "PRIMARY KEY(ID) );";

    private final String tabelaPlanoDeSaude = "CREATE TABLE IF NOT EXISTS PLANO_DE_SAUDE("
            + "ID INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1),"
            + "CODIGO_DO_PLANO VARCHAR(10) NOT NULL,"
            + "TELEFONE VARCHAR(20) NOT NULL,"
            + "ENDERECO VARCHAR(60) NOT NULL,"
            + "REGISTRO_ANS VARCHAR(10) NOT NULL,"
            + "PRIMARY KEY(ID) );";

    public void construirTabelas() {

        try {

            executarScriptSql(tabelaPerfil);
            executarScriptSql(tabelaUsuario);
            executarScriptSql(tabelaMedico);
            executarScriptSql(tabelaPaciente);
            executarScriptSql(tabelaConsulta);
            executarScriptSql(tabelaPlanoDeSaude);

        } catch (SQLException ex) {

            System.out.println(ex);

        }

    }

    public void inserirPerfisIniciais() {

        PerfilDAO daoPerfil = new PerfilDAO();

        if (daoPerfil.buscarPorId(101).getNome() == null) {

            try {

                String sql = "INSERT INTO PERFIL( "
                        + "NOME, DESCRICAO)VALUES "
                        + "('ADMINISTRADOR','SUPER USUÁRIO'),"
                        + "('COMUM','USUÁRIO SÓ DE CONSULTAS DE DADOS'),"
                        + "('AVANÇADO','USUÁRIO CONSULTA E CADASTRA');";

                executarScriptSql(sql);

            } catch (SQLException ex) {

                System.out.println(ex);

            }

        }

    }

    public void inserirUsuariosIniciais() {

        UsuarioDAO daoUsuario = new UsuarioDAO();

        if (daoUsuario.buscarPorId(100).getNome() == null) {

            try {

                String sql = "INSERT INTO USUARIO ( CPF,NOME,USERNAME,SENHA,CODIGO_PERFIL,TELEFONE,MATRICULA,DATA_NASCIMENTO) VALUES "
                        + "('865741361100','Iolanda Caixeta','iolanda.ic','rsa01',102,'21044555',21301,'1978-08-16'),"
                        + "('014876554733','Gladstone Castro','gladstone.gc','ipv06',101,'21120988',21402,'1977-09-21'),"
                        + "('103488774443','João Paulo','joao.jp','ssdh',103,'21356678',21503,'1975-12-14'),"
                        + "('765441112276','Florinda Justos','florinda.fj','atm02',103,'22358901',21588,'1980-11-24'),"
                        + "('967741364100','Italo Silva','italo.is','rist3',102,'24044555',21677,'1971-08-22'),"
                        + "('114876854235','Mirela Couto','mirela.mc','pat45',101,'26120988',21843,'1962-06-26')";

                executarScriptSql(sql);

            } catch (SQLException ex) {

                System.out.println(ex);

            }

        }

    }

    public void inserirMedicosIniciais() throws SQLException {

        MedicoDAO daoMedico = new MedicoDAO();

        if (daoMedico.buscarPorId(122).getNome() == null) {

            try {

                String sql = "INSERT INTO MEDICO ( CRM,NOME,ESPECIALIDADE,SEXO,DATA_NASCIMENTO,SALARIO) VALUES "
                        + "(122,'VANESSA','CLINICA MEDICA','F','1965-01-01',5200.00),"
                        + "(123,'MICHAEL','OTORRINO','M','1973-10-10',5275.00),"
                        + "(124,'PAULO','PEDIATRA','M','1975-04-05',5300.00),"
                        + "(125,'FERNANDA','CARDIOLOGISTA','F','1973-09-14',5800.00),"
                        + "(126,'RUBENS','DERMATOLOGISTA','M','1980-09-30',5700.00),"
                        + "(127,'HELENA,'PEDIATRA','F',1973-06-01', 5900.00)";

                executarScriptSql(sql);

            } catch (SQLException ex) {

                System.out.println(ex);

            }

        }

    }

    public void inserirPacientesIniciais() throws SQLException {

        PacienteDAO daoPaciente = new PacienteDAO();

        if (daoPaciente.buscarPorId(1).getNome() == null) {

            try {

                String sql = "INSERT INTO PACIENTE ( ID,CPF,NOME,TELEFONE,DATA_NASCIMENTO,ENDERECO,SEXO) VALUES "
                        + "(1,'85868936199','MARIA DO CARMO','(61)985643322','1945-06-16','AGUAS CLARAS','F'),"
                        + "(2,'03388345577','LUIS CARLOS','(61)34098766 ','1993-08-10','ASA SUL','M'),"
                        + "(3,'01229877654','FABIO JUNIOR','(61)58574583','1975-12-29','CEILANDIA NORTE','M'),"
                        + "(4,'56788995567','ANA LUISA','(61)976544563','1983-09-01','NUCLEO BANDEIRANTE','F'),"
                        + "(5,'22214557669','HAILTON MORAIS','(61)776544987','1986-09-22','AREAL','M'),"
                        + "(6,'76812345567','KATIA AZEVEDO','(61)33732775','1973-06-01','TAGUATINGA SUL','F'),";

                executarScriptSql(sql);

            } catch (SQLException ex) {

                System.out.println(ex);

            }

        }

    }

    public void inserirConsultasIniciais() throws SQLException {

        ConsultaDAO daoConsulta = new ConsultaDAO();

        if (daoConsulta.buscarPorId(100).getId() == null) {

            try {

                String sql = "INSERT INTO CONSULTA ( ID,ID_PLANO,ID_PACIENTE,CRM_MEDICO,SALA,DATA_DA_CONSULTA,HORA_DA_CONSULTA) VALUES "
                        + "(100,5,1,127,'A2','2017-05-16,'16:30'),"
                        + "(101,6,2,123,'A7','2016-08-10,'09:45'),"
                        + "(102,4,3,124,'B9','2017-01-29,'10:00'),"
                        + "(103,1,4,125,'C4','2017-06-01,'16:30'),"
                        + "(104,3,5,122,'A2','2015-09-22,'17:10'),"
                        + "(105,2,6,126,'B3','2015-05-01,'07:45'),";

                executarScriptSql(sql);

            } catch (SQLException ex) {

                System.out.println(ex);

            }

        }

    }

    public void inserirPlanosDeSaudeIniciais() throws SQLException {

        PlanoDeSaudeDAO daoPlanoDeSaude = new PlanoDeSaudeDAO();

        if (daoPlanoDeSaude.buscarPorId(1).getId() == null) {
            
            try {

                String sql = "INSERT INTO PLANO_DE_SAUDE ( ID,CODIGO_DO_PLANO,OPERADORA,TELEFONE,ENDERECO,REGISTRO_ANS) VALUES "
                        + "(1,'AM14','AMIL','87563327','AGUAS CLARAS','ANS00133'),"
                        + "(2,'ME23','MEDIAL','36098768','ASA SUL','ANS00135'),"
                        + "(3,'CAS01','CASSI','58576554','CEILANDIA NORTE','ANS00137'),"
                        + "(4,'ALL03 ','ALLIANCE','976875433','NÚCLEO BANDEIRANTE','ANS00139),"
                        + "(5,'BRA81','BRADESCO','987774566','AGUAS CLARAS','ANS00133'),"
                        + "(6,'AM14','AMIL','87563327','AREAL','ANS00143'),";

                executarScriptSql(sql);

            } catch (SQLException ex) {

                System.out.println(ex);

            }

        }
    }

    void inserirDados() {
   
    }

}
