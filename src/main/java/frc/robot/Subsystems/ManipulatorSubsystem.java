// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ManipulatorSubsystem extends SubsystemBase {
  public static SparkMax manipulatorDriver = new SparkMax(Constants.ManipulatorConstants.manipulatorDriverID, MotorType.kBrushless);
  public static SparkMax manipulatorPassenger = new SparkMax(Constants.ManipulatorConstants.manipulatorPassengerID, MotorType.kBrushless);

  private static SparkMaxConfig PassengerConfig = new SparkMaxConfig();
  private static SparkMaxConfig DriverConfig = new SparkMaxConfig();

  /** Creates a new ManipulatorSubsystem. */
  public ManipulatorSubsystem() {
    configManipulator();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public static void runManipulator(double speed){
    manipulatorDriver.set(speed);
  }

  public static void stopManipulator(){
    manipulatorDriver.set(0);
  }

  private static void configManipulator(){//figure out how to get one motor to follow the other
    PassengerConfig.follow(manipulatorDriver);
    PassengerConfig.inverted(false);

    DriverConfig.inverted(true);

    manipulatorPassenger.configure(PassengerConfig, null, PersistMode.kPersistParameters);
    manipulatorDriver.configure(DriverConfig, null, PersistMode.kPersistParameters);
  }
}
