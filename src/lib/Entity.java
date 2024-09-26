package lib;

import java.util.Arrays;

/**
 * Represents an object that has an associated gameObject.
 */
public interface Entity {
	GameObject getGameObject();

	default void addChild(Entity child){
		getGameObject().addChild(child.getGameObject());
	}
	default void addChild(Entity... children){
		Arrays.stream(children).forEach(this::addChild);
	}
}