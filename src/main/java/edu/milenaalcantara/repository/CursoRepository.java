package edu.milenaalcantara.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static edu.milenaalcantara.connection.ConnectionDB.connect;

public class CursoRepository {
    private Connection conn = connect();
    private PreparedStatement pstm;

    public CursoRepository() {
    }
    public CursoRepository(Connection connection) {
        this.conn = connection;
    }

    public Boolean consultarCursosPorProfessor(String nomeProfessor){
        try {
            String SQL= "SELECT cursos.nome_curso " +
                    "FROM cursos " +
                    "LEFT JOIN professor " +
                    "ON professor.id_professor = cursos.id_curso " +
                    "WHERE professor.nome_professor =?";
            pstm = conn.prepareStatement(SQL);
            pstm.setString(1,nomeProfessor);
            ResultSet resultSet = pstm.executeQuery();
            System.out.println("Professor responsável:  "+nomeProfessor);
            System.out.println("---------- Cursos ----------");
            while (resultSet.next()){
                System.out.println("Nome do Curso: "+resultSet.getString("nome_curso"));
            }
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean consultarCursosSemAlunosMatriculados(){
        try {
            String SQL = "SELECT cursos.nome_curso " +
                    "FROM cursos " +
                    "FULL JOIN matriculas " +
                    "ON cursos.id_curso = matriculas.curso_id " +
                    "WHERE matriculas.aluno_id IS NULL;";
            pstm = conn.prepareStatement(SQL);
            ResultSet resultSet = pstm.executeQuery();
            System.out.println("Cursos sem alunos matriculados: ");
            while (resultSet.next()){
              System.out.println("Nome do Curso: "+resultSet.getString("nome_curso"));
            }
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
