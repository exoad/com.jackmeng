package com.jackmeng.stl;

public class stl_PIDController
{
    private final double P;
    private final double I;
    private final double D;
    private double setpoint;
    private double errorSum;
    private double lastError;
    private double maxErrorSum;
    private double maxOutput;
    private double minOutput;
    private double lastTime;
    private double dt;
    private double deadband;
    private boolean firstRun;
    private boolean isOnTarget;
    private double onTargetError;
    private int onTargetCount;
    private int onTargetCountMax;

<<<<<<< HEAD
    public stl_PIDController(double P, double I, double D) 
=======
    stl_PIDController(double P, double I, double D)
>>>>>>> 0b00d40 (`add`)
    {
        this.P = P;
        this.I = I;
        this.D = D;
        errorSum = 0;
        lastError = 0;
        maxErrorSum = Double.MAX_VALUE;
        maxOutput = Double.MAX_VALUE;
        minOutput = -Double.MAX_VALUE;
        lastTime = System.nanoTime();
        dt = 0.01;
        deadband = 0;
        firstRun = true;
        isOnTarget = false;
        onTargetError = 0.1;
        onTargetCount = 0;
        onTargetCountMax = 10;
    }

<<<<<<< HEAD
    public void setSetpoint(double setpoint) 
=======
    void setSetpoint(double setpoint)
>>>>>>> 0b00d40 (`add`)
    {
        this.setpoint = setpoint;
    }

<<<<<<< HEAD
    public void setMaxErrorSum(double maxErrorSum) 
=======
    void setMaxErrorSum(double maxErrorSum)
>>>>>>> 0b00d40 (`add`)
    {
        this.maxErrorSum = maxErrorSum;
    }

<<<<<<< HEAD
    public void setMaxOutput(double maxOutput) 
=======
    void setMaxOutput(double maxOutput)
>>>>>>> 0b00d40 (`add`)
    {
        this.maxOutput = maxOutput;
    }

<<<<<<< HEAD
    public void setMinOutput(double minOutput) 
=======
    void setMinOutput(double minOutput)
>>>>>>> 0b00d40 (`add`)
    {
        this.minOutput = minOutput;
    }

<<<<<<< HEAD
    public void setDt(double dt) 
=======
    void setDt(double dt)
>>>>>>> 0b00d40 (`add`)
    {
        this.dt = dt;
    }

<<<<<<< HEAD
    public void setDeadband(double deadband) 
=======
    void setDeadband(double deadband)
>>>>>>> 0b00d40 (`add`)
    {
        this.deadband = deadband;
    }

<<<<<<< HEAD
    public void setOnTargetError(double onTargetError) 
=======
    void setOnTargetError(double onTargetError)
>>>>>>> 0b00d40 (`add`)
    {
        this.onTargetError = onTargetError;
    }

<<<<<<< HEAD
    public void setOnTargetCountMax(int onTargetCountMax) 
=======
    void setOnTargetCountMax(int onTargetCountMax)
>>>>>>> 0b00d40 (`add`)
    {
        this.onTargetCountMax = onTargetCountMax;
    }

<<<<<<< HEAD
    public double calculate(double processVariable) 
    {
        double time = System.nanoTime();
        if (firstRun) 
=======
    double calculate(double processVariable)
    {
        double time = System.nanoTime();
        if (firstRun)
>>>>>>> 0b00d40 (`add`)
        {
            dt = (time - lastTime) / 1e9;
            firstRun = false;
        }
        double error = setpoint - processVariable;
        if (Math.abs(error) < deadband)
<<<<<<< HEAD
=======
        {
>>>>>>> 0b00d40 (`add`)
            error = 0;
        errorSum += error * dt;
        errorSum = Math.min(errorSum, maxErrorSum);
        double dError = (error - lastError) / dt;
        double output = P * error + I * errorSum + D * dError;
        output = Math.max(Math.min(output, maxOutput), minOutput);
        lastError = error;
        lastTime = time;
        checkOnTarget(error);
        return output;
    }

<<<<<<< HEAD
    private void checkOnTarget(double error) 
    {
        if (Math.abs(error) < onTargetError) 
        {
            onTargetCount++;
            if (onTargetCount >= onTargetCountMax)
                isOnTarget = true;
        } 
        else 
=======
    private void checkOnTarget(double error)
    {
        if (Math.abs(error) < onTargetError)
        {
            onTargetCount++;
            if (onTargetCount >= onTargetCountMax)
            {
                isOnTarget = true;
            }
        }
        else
>>>>>>> 0b00d40 (`add`)
        {
            isOnTarget = false;
            onTargetCount = 0;
        }
    }

<<<<<<< HEAD
    public boolean isOnTarget() 
=======
    public boolean isOnTarget()
>>>>>>> 0b00d40 (`add`)
    {
        return isOnTarget;
    }

<<<<<<< HEAD
    public void reset() 
=======
    public void reset()
>>>>>>> 0b00d40 (`add`)
    {
        errorSum = 0;
        lastError = 0;
        isOnTarget = false;
        onTargetCount = 0;
    }
}
