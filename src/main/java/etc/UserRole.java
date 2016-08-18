package etc;

/**
 * Created by Diego on 31-07-2016.
 */
public enum UserRole {

	ADMINISTRADOR(1),
	EMPRESA(2),
	ESTUDIANTE(3),
	DIRECTOR(4);

	private final int value;

	UserRole(final int newValue) {
		value = newValue;
	}

	public int getValue() { return value; }
}
