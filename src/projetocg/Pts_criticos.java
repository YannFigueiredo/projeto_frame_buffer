package projetocg;

public class Pts_criticos implements Comparable<Pts_criticos>{
    int index;
    int dir;
    float x_intersection;
    float inv_slope;
    
    public Pts_criticos(int index1, int dir1, float x_intersection1, float inv_slope1){
        index = index1;
        dir = dir1;
        x_intersection = x_intersection1;
        inv_slope = inv_slope1;
    }
    
    public float getXIntersection(){
        return x_intersection;
    }

    @Override
    public int compareTo(Pts_criticos pt_critico) {
        return (int) (this.x_intersection - pt_critico.getXIntersection());
    }
}
