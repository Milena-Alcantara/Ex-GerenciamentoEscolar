import edu.milenaalcantara.repository.AlunoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AlunoRepositoryTest {
    private AlunoRepository alunoRepository;
    private Connection connect = mock(Connection.class);
    private PreparedStatement statement = mock(PreparedStatement.class);
    private ResultSet result = mock(ResultSet.class);
    private ByteArrayOutputStream capturaASaidaDoConsole = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        try {
            when(connect.prepareStatement(anyString())).thenReturn(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.setOut(new PrintStream(capturaASaidaDoConsole));
        alunoRepository = new AlunoRepository(connect);

    }
    @Test
    void testConsultaDeAlunosMatriculadosEmUmCursoEspecifico() throws SQLException {
        try {
            when(connect.prepareStatement(anyString())).thenReturn(statement);
            when(statement.executeQuery()).thenReturn(result);
            when(result.next()).thenReturn(true).thenReturn(false);
            when(result.getString("nome_aluno")).thenReturn("Amanda");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(alunoRepository.consultarAlunosPorCurso("Desenvolvimento Mobile"));
    }
    @Test
    void testConsultaDeAlunosQueNaoEstaoMatriculadosEmNenhumCurso() throws SQLException {
        when(statement.executeQuery()).thenReturn(result);
        when(result.next()).thenReturn(true).thenReturn(false);
        when(result.getString("nome_aluno")).thenReturn("Vitoria Cerqueira");
        Assertions.assertTrue(alunoRepository.consultarAlunosNaoMatriculados());
    }

    @Test
    void testConsultaDeAlunosMatriculadosEmMaisDeUmCurso() throws SQLException {
        when(statement.executeQuery()).thenReturn(result);
        when(result.next()).thenReturn(true).thenReturn(false);
        when(result.getString("nome_aluno")).thenReturn("Amanda Souza");
        Assertions.assertTrue(alunoRepository.consultarAlunosMatriculadosEmMaisDeUmCurso());
    }
}