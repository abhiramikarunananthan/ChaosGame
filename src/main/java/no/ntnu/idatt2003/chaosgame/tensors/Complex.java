package no.ntnu.idatt2003.chaosgame.tensors;
public class Complex extends Vector2D {

    public Complex(double realPart, double imaginaryPart) {
        super(realPart, imaginaryPart);
    }

    public Complex square(){
        double newRealPart = this.getX0() * this.getX0();
        double newImaginaryPart = this.getX1() * this.getX1();
        return new Complex(newRealPart, newImaginaryPart);
    }


    public Complex sqrt(){
       double newRealPart  = Math.sqrt(0.5*(Math.sqrt(Math.pow(this.getX0(), 2) +Math.pow(this.getX1(), 2) ) + this.getX0()));
       double newImaginaryPart = Math.sqrt(0.5*(Math.sqrt(Math.pow(this.getX0(), 2) +Math.pow(this.getX1(), 2) ) - this.getX0()));
        return new Complex(newRealPart, newImaginaryPart);
    }

}
