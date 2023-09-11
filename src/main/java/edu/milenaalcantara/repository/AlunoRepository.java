package edu.milenaalcantara.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static edu.milenaalcantara.connection.ConnectionDB.connect;
public class AlunoRepository {
    private Connection conn = connect();
    private PreparedStatement pstm;

    public AlunoRepository() {
    }
    public AlunoRepository(Connection connection) {
        this.conn = connection;
    }
    public Boolean consultarAlunosPorCurso(String nomeCurso){
        String SQL = "SELECT alunos.nome_aluno FROM alunos INNER JOIN matriculas ON alunos.id_aluno=matriculas.aluno_id " +
                "INNER JOIN cursos ON cursos.id_curso=matriculas.curso_id " +
                "WHERE cursos.nome_curso=?";
        try {
            pstm = conn.prepareStatement(SQL);
            pstm.setString(1,nomeCurso);
            ResultSet resultSet = pstm.executeQuery();

            System.out.println("Alunos matriculados no curso: "+nomeCurso);
            while (resultSet.next()){
                System.out.println("Nome: "+resultSet.getString("nome_aluno"));
            }
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Boolean consultarAlunosNaoMatriculados(){
        try {
            String SQL = "SELECT alunos.nome_aluno " +
                    "FROM matriculas " +
                    "RIGHT JOIN alunos ON alunos.id_aluno = matriculas.aluno_id " +
                    "WHERE matriculas.curso_id IS NULL;";
            pstm = conn.prepareStatement(SQL);
            ResultSet resultSet = pstm.executeQuery();
            System.out.println("Alunos não matriculados em cursos: ");
            while (resultSet.next()){
                System.out.println("Nome: "+resultSet.getString("nome_aluno"));
            }
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Boolean consultarAlunosMatriculadosEmMaisDeUmCurso(){
        try {
            String SQL = "SELECT alunos.nome_aluno " +
                    "FROM alunos " +
                    "INNER JOIN matriculas M1 ON alunos.id_aluno = M1.aluno_id " +
                    "INNER JOIN matriculas M2 ON alunos.id_aluno = M2.aluno_id " +
                    "WHERE M1.curso_id <> M2.curso_id;";
            pstm = conn.prepareStatement(SQL);
            ResultSet resultSet = pstm.executeQuery();
            System.out.println("Alunos não matriculados em cursos: ");
            while (resultSet.next()){
                System.out.println("Nome: "+resultSet.getString("nome_aluno"));
            }
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

