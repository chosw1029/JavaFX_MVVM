package com.nextus.mvvmfx.ui.alertmgmt.animations;


import javafx.stage.Stage;

import java.util.function.Function;

public enum Animations {

	SLIDE(SlideAnimation::new),
	FADE(FadeAnimation::new),
	POPUP(PopupAnimation::new);

	private final Function<Stage, Animation> newInstance;

	Animations(Function<Stage, Animation> newInstance) {
		this.newInstance = newInstance;
	}

	public Animation newInstance(Stage stage) {
		return newInstance.apply(stage);
	}

}
