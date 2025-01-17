// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Commands.ElevatorControl;
import frc.robot.Subsystems.ElevatorSubsystem;

public class RobotContainer {

  //Controller Instantiations
  public static final CommandXboxController driver = new CommandXboxController(0);

  //Subsystem Instantiations
  private static ElevatorSubsystem mElevatorSubsystem = new ElevatorSubsystem();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    //BUTTON BINDINGS YAY!!!
    driver.y().whileTrue(new ElevatorControl(mElevatorSubsystem, 0.5));//runs the Elevator at a constant percent speed -1.0 to 1.0 vals
    driver.a().whileTrue(new ElevatorControl(mElevatorSubsystem, -0.5));//runs the Elevator at a constant percent speed -1.0 to 1.0 vals
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
