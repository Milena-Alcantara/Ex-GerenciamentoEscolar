import edu.milenaalcantara.repository.CursoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CursoRepositoryTest {
    private CursoRepository cursoRepository;
    private Connection connect = mock(Connection.class);
    private PreparedStatement statement = mock(PreparedStatement.class);
    private ResultSet result = mock(ResultSet.class);

    @BeforeEach
    void setUp() {
        try {
            when(connect.prepareStatement(anyString())).thenReturn(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cursoRepository = new CursoRepository(connect);
    }

    @Test
    void testConsultaDeCursosMinistradosPorUmProfessor() throws SQLException {
        when(statement.executeQuery()).thenReturn(result);
        when(result.next()).thenReturn(true).thenReturn(false);
        when(result.getString("nome")).thenReturn("Pequenos gênios");
        Assertions.assertTrue(cursoRepository.consultarCursosPorProfessor("Roberta"));
    }

    @Test
    void testConsultaDeCursosSemAlunosMatriculados() throws SQLException {
        when(statement.executeQuery()).thenReturn(result);
        when(result.next()).thenReturn(true).thenReturn(false);
        when(result.getString("nome")).thenReturn("H20");
        Assertions.assertTrue(cursoRepository.consultarCursosSemAlunosMatriculados());
    }

}
