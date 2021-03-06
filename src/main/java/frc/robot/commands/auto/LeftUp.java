/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.chassis.TrajectoryDrivetrain;
import frc.robot.subsystems.chassis.TrajectoryFactory;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class LeftUp extends SequentialCommandGroup {
  /**
   * Creates a new LeftUp.
   */
  public LeftUp(TrajectoryDrivetrain drivetrain) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new InstantCommand(()-> TrajectoryFactory.getTrajectory("output/LeftUp.wpilib.json")),
      new InstantCommand(()-> TrajectoryFactory.initPose(drivetrain)),
      new RamseteCommand(TrajectoryFactory.getTrajectory("output/LeftUp.wpilib.json"), drivetrain::getPose2d, new RamseteController(2.0, 0.7), drivetrain.getFeedforward(), drivetrain.getKinematics(), drivetrain::getSpeed, drivetrain.getlpidcontroller(), drivetrain.getrpidcontroller(), drivetrain::setOutput, drivetrain),
      new InstantCommand(()-> drivetrain.setOutput(0, 0))
      );
  }
}
