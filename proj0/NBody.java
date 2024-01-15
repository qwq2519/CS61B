public class NBody {
    public static double readRadius(String fileName){
        In in=new In(fileName);
        int number=in.readInt();
        double radius=in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName){
        In in=new In(fileName);
        int number=in.readInt();
        double radius=in.readDouble();
        Planet[] planets=new Planet[number];
        for(int i=0;i<number;++i){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double vX = in.readDouble();
            double vY = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xP,yP,vX,vY,m,img);
        }
        return planets;
    }

    public static void main(String args[]){
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];

        double radius=readRadius(filename);
        Planet[] planets=readPlanets(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(radius,-radius);
        StdDraw.picture(0,0,"images/starfield.jpg");

        for(Planet p:planets){
            p.draw();
        }
        StdDraw.show();

        double[] xforce=new double[planets.length];
        double[] yforce=new double[planets.length];
        for(double time=0;time<=T;time+=dt){

            for(int i=0;i< planets.length;++i){
                xforce[i]=planets[i].calcNetForceExertedByX(planets);
                yforce[i]=planets[i].calcNetForceExertedByY(planets);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");

            for(int i=0;i< planets.length;++i){
                planets[i].update(dt,xforce[i],yforce[i]);
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);


        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}