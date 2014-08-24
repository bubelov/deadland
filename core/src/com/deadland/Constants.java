package com.deadland;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public interface Constants {
    int SCREEN_WIDTH = 1920;
    int SCREEN_HEIGHT = 1080;

    float METER_PER_PIXEL = 0.001f;
    float PIXEL_PER_METER = 1000;

    float timeStep = 1 / 300f;
    //todo checks following values
    int velocityIterations = 6;
    int positionIterations = 2;
}
