public class Planet {

    /*two letter to reduce write error*/
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double graConstant = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((xxPos - p.xxPos) * (xxPos - p.xxPos) + (yyPos - p.yyPos) * (yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        return graConstant * (mass * p.mass) / (calcDistance(p) * calcDistance(p));
    }
    public double calcForceExertedByX(Planet p){
        double dx=p.xxPos-xxPos;
        return calcForceExertedBy(p)*dx/calcDistance(p);
    }
    public double calcForceExertedByY(Planet p){
        double dy=p.yyPos-yyPos;
        return calcForceExertedBy(p)*dy/calcDistance(p);
    }
    public double calcNetForceExertedByX(Planet[] p){
        double netForce=0.0;
        for(Planet i:p){
            if(this.equals(i)) continue;;
            netForce+=this.calcForceExertedByX(i);
        }
        return netForce;
    }
    public double calcNetForceExertedByY(Planet[] p){
        double netForce=0.0;
        for(Planet i:p){
            if(this.equals(i)) continue;;
            netForce+=this.calcForceExertedByY(i);
        }
        return netForce;
    }

    public void update(double dt,double fX,double fY){
        double aX=fX/mass;
        double aY=fY/mass;

        xxVel+=dt*aX;
        yyVel+=dt*aY;

        xxPos+=dt*xxVel;
        yyPos+=dt*yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }
}
