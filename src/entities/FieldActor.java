package entities;

public class FieldActor extends AbstractActor {

	public FieldActor(String id, int spawn_position_x, int spawn_position_y, String imagepath, boolean collisonOn,
			ActorsEnum type) {
		super(id, spawn_position_x, spawn_position_y, imagepath, collisonOn, type);
	}

}
