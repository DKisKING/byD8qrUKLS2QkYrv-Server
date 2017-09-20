package com.vencillio.rs2.content.skill.mining;

import com.vencillio.core.cache.map.RSObject;
import com.vencillio.core.cache.map.Region;
import com.vencillio.core.task.Task;
import com.vencillio.core.task.impl.TaskIdentifier;
import com.vencillio.rs2.entity.object.GameObject;
import com.vencillio.rs2.entity.object.ObjectManager;

public class deadoretask extends Task {
	private GameObject object;
	private final int Ore;

	public deadoretask(GameObject object, int Ore, int delay) {
		super(delay, false, Task.StackType.STACK, Task.BreakType.NEVER, TaskIdentifier.CURRENT_ACTION);
		this.Ore = Ore;
		this.object = object;
	}

	@Override
	public void execute() {
		ObjectManager.removeFromList(object);

		RSObject rsObject = new RSObject(object.getLocation().getX(), object.getLocation().getY(), object.getLocation().getZ(), Ore, 10, 0);
		Region.getRegion(object.getLocation().getX(), object.getLocation().getY()).addObject(rsObject);

		ObjectManager.send(new GameObject(Ore, object.getLocation().getX(), object.getLocation().getY(), object.getLocation().getZ(), 10, 0));
		ObjectManager.register(new GameObject(Ore, object.getLocation().getX(), object.getLocation().getY(), object.getLocation().getZ(), object.getType(), object.getFace()));
		stop();
	}

	@Override
	public void onStop() {
	}
}
