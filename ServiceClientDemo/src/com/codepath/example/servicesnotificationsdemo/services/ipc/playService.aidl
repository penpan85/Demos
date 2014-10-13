package com.codepath.example.servicesnotificationsdemo.services.ipc;

interface playService {

	void play();

	void pause();

	void stop();

	int getDuration();

	int getCurrentPosition();
	void seekTo(int current);
	boolean setLoop(boolean loop);
}