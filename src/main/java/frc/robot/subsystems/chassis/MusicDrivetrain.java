/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.chassis;

import com.ctre.phoenix.music.Orchestra;

/**
 * Add your docs here.
 */
public class MusicDrivetrain extends DrivetrainBase{
    static Orchestra orchestra = new Orchestra();
    static{
        orchestra.addInstrument(leftMas);
        orchestra.addInstrument(leftFol);
        orchestra.addInstrument(rightMas);
        orchestra.addInstrument(rightFol);
    }
    public static void start(String path){
        orchestra.loadMusic(path);
        orchestra.play();
    }
    public static void stop(){
        orchestra.stop();
    }
    

}
