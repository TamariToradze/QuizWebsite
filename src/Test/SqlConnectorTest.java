package Test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.SQLConnector;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;


public class SqlConnectorTest {
    private SQLConnector sqlConnector;

    @Before
    public void setUp() {
        sqlConnector = new SQLConnector();
    }

    @After
    public void closing() {
        try {
            SQLConnector.dataSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dataSourceTest() {
        assertNotNull(SQLConnector.dataSource);
        String expectedURL = "jdbc:mysql://localhost:3306/OOP_QUIZ";
        String expectedUsername = "root";
        String expectedPassword = "ninimagaria123";
        assertEquals(expectedURL, SQLConnector.dataSource.getUrl());
        assertEquals(expectedUsername, SQLConnector.dataSource.getUsername());
        assertEquals(expectedPassword, SQLConnector.dataSource.getPassword());
    }

    @Test
    public void connectionTest() throws SQLException {
        try (Connection connection = SQLConnector.getConnection()) {
            assertNotNull(connection);
            assertFalse(connection.isClosed());
        }
    }

    @Test
    public void multipleConnectionsTest() throws SQLException {
        Connection connection_1 = null;
        Connection connection_2 = null;

        try {
            connection_1 = SQLConnector.getConnection();
            connection_2 = SQLConnector.getConnection();
            assertNotNull(connection_1);
            assertNotNull(connection_2);
            assertNotSame(connection_1, connection_2);
        } finally {
            if (connection_1 != null) connection_1.close();
            if (connection_2 != null) connection_2.close();
        }
    }
}