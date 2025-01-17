// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {
  public static TalonFX elevatorMotor = new TalonFX(Constants.ElevatorConstants.elevatorId);
  public static TalonFXConfiguration elevatorConfiguration = new TalonFXConfiguration();
  private static final PositionDutyCycle elevatorPosCycle = new PositionDutyCycle(0);

  public static double lastElevatorPosition; 

  //creates a public enum that can have 4 different states of the elevator to corespond to different control modes
  public static enum ElevatorState {
    GoingUp,
    GoingDown,
    Idle,
    HoldingPosition
  }

  public static ElevatorState mElevatorState; 

  /** Creates a new ElevatorSubsystem. */
  public ElevatorSubsystem() {
    resetElevator();//resets the elevator and sends the configs to the motor
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Elevator Pos", elevatorMotor.getPosition().getValueAsDouble());//sends the position of the elevator to the Dashboard
  }

  public static void setElevatorPos(double position){//PID control of the position of the elevator
    elevatorMotor.setControl(elevatorPosCycle.withPosition(position));
  }

  public static void setElevator(double speed){//Percent output of the elevator
    elevatorMotor.set(speed);
  }

  public static void stopElevator(){//stops the elevator
    elevatorMotor.set(0);
  }

  public static void resetElevator(){//configures all of the values for PID and eventually Motion Magic --- Slot0 holds regular PID vals
    elevatorConfiguration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

    elevatorConfiguration.Slot0.kP = 0.1;
    elevatorConfiguration.Slot0.kI = 0;
    elevatorConfiguration.Slot0.kD = 0;

    elevatorConfiguration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

    elevatorMotor.setPosition(0);

    elevatorPosCycle.Slot = 0;

    elevatorMotor.getConfigurator().apply(elevatorConfiguration);
  }
}
