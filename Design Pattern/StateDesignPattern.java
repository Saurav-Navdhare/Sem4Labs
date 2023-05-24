// Define the interface for the state
interface ConnectionState {
    void connect();
    void disconnect();
    void report();
}

// Define the first state of the connection
class DisconnectedState implements ConnectionState {
    private final Connection connection;

    DisconnectedState(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void connect() {
        System.out.println("Connecting...");
        connection.setState(new ConnectedState(connection));
    }

    @Override
    public void disconnect() {
        System.out.println("Already disconnected.");
    }

    @Override
    public void report() {
        System.out.println("The connection is currently disconnected.");
    }
}

// Define the second state of the connection
class ConnectedState implements ConnectionState {
    private final Connection connection;

    ConnectedState(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void connect() {
        System.out.println("Already connected.");
    }

    @Override
    public void disconnect() {
        System.out.println("Disconnecting...");
        connection.setState(new DisconnectedState(connection));
    }

    @Override
    public void report() {
        System.out.println("The connection is currently connected.");
    }
}

// Define the context class that manages the state of the connection
class Connection {
    private ConnectionState state;

    Connection() {
        this.state = new DisconnectedState(this);
    }

    void setState(ConnectionState state) {
        this.state = state;
    }

    void connect() {
        state.connect();
    }

    void disconnect() {
        state.disconnect();
    }

    void report() {
        state.report();
    }
}

// Example usage
public class StateDesignPattern {
    public static void main(String[] args) {
        Connection connection = new Connection();
        connection.report(); // Output: The connection is currently disconnected.
        connection.connect(); // Output: Connecting...
        connection.report(); // Output: The connection is currently connected.
        connection.disconnect(); // Output: Disconnecting...
        connection.report(); // Output: The connection is currently disconnected.
    }
}
