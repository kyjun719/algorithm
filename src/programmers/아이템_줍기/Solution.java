package programmers.아이템_줍기;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        List<Rect> rectList = new ArrayList<>();
        for(int i=0; i<rectangle.length; i++){
            rectList.add(new Rect(i, rectangle[i], getLineListFromPoint(rectangle[i])));
        }

        //교차점으로 선분자르기
        List<Line> list = new ArrayList<>();
        for(Rect rect: rectList) {
            for(Line line: rect.lineList) {
                List<Vector> vectorList = new ArrayList<>();
                vectorList.add(line.a);
                vectorList.add(line.b);

                for(int i = 0; i < rectList.size(); i++) {
                    if(i == rect.idx) {
                        continue;
                    }

                    for(Line other: rectList.get(i).lineList) {
                        if(line.isCross(other)) {
//                            System.out.println(line+","+other+">>"+line.getCrossPoint(other));
                            vectorList.add(line.getCrossPoint(other));
                        }
                    }
                }

                vectorList.sort(new Comparator<Vector>() {
                    @Override
                    public int compare(Vector o1, Vector o2) {
                        return o1.greaterThen(o2)? -1 : 1;
                    }
                });

                for(int i = 0; i < vectorList.size()-1; i++) {
                    list.add(new Line(vectorList.get(i), vectorList.get(i+1)));
                }
            }
        }

        list.sort(new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                return (int)(o1.a.x-o2.a.x);
            }
        });

//        System.out.println(list);

        //직사각형내 포함되는 선분제거
        List<Line> conv = new ArrayList<>();
        for(Line line : list) {
            boolean contained = false;
            for(Rect rect : rectList) {
                if(rect.containsLine(line)) {
                    contained = true;
                    break;
                }
            }
            if(!contained) {
                conv.add(line);
            }
        }

//        System.out.println(conv);

        HashMap<Vector, Integer> map = new HashMap<>();

        Vector endVector = new Vector(itemX, itemY);
        int answer = 987654321;
        Queue<LineMoveInfo> infos = new LinkedList<>();
        infos.add(new LineMoveInfo(new Vector(characterX, characterY), 0));
        while (!infos.isEmpty()) {
            LineMoveInfo now = infos.poll();
//            System.out.println(now.vector+">>"+now.moved);

            if(now.moved >= answer) {
                continue;
            }

            for(Line line : conv) {
                if(line.isInvolved(now.vector)) {
                    if(line.isInvolved(endVector)) {
//                        System.out.println(pointToLine(now.vector, line.a, line.b));
//                        System.out.println("finish::"+line+">>"+now.vector+","+endVector);
                        answer = Math.min(answer, now.moved+(int)(now.vector.minus(endVector).norm()));
                        map.put(endVector, answer);
                        continue;
                    }

                    int na = now.moved+(int)(line.a.minus(now.vector).norm());
                    if(map.get(line.a) == null || map.get(line.a) > na) {
                        infos.add(new LineMoveInfo(line.a, na));
                        map.put(line.a, na);
                    }

                    int nb = now.moved+(int)(line.b.minus(now.vector).norm());
                    if(map.get(line.b) == null || map.get(line.b) > nb) {
                        infos.add(new LineMoveInfo(line.b, nb));
                        map.put(line.b, nb);
                    }
                }
            }
        }

//        System.out.println(answer);

        return answer;
    }

    private List<Line> getLineListFromPoint(int[] arr) {
        int lx = arr[0];
        int ly = arr[1];
        int rx = arr[2];
        int ry = arr[3];

        List<Line> list = new ArrayList<>();
        list.add(new Line(new Vector(lx, ly), new Vector(lx, ry)));
        list.add(new Line(new Vector(lx, ry), new Vector(rx, ry)));
        list.add(new Line(new Vector(lx, ly), new Vector(rx, ly)));
        list.add(new Line(new Vector(rx, ly), new Vector(rx, ry)));

        return list;
    }


    class LineMoveInfo {
        int moved = 987654321;
        Vector vector;

        LineMoveInfo(Vector vector, int moved) {
            this.vector = vector;
            this.moved = moved;
        }
    }

    class Rect {
        int[] rectPoint;
        List<Line> lineList = new ArrayList<>();
        int idx;

        Rect(int idx, int[] rectPoint, List<Line> lineList) {
            this.idx = idx;
            this.rectPoint = rectPoint;
            this.lineList = lineList;
        }

        boolean containsLine(Line line) {
            Vector center = line.a.plus(line.b).div(2);
            return containsPoint(line.a) || containsPoint(line.b) || containsPoint(center);
        }

        private boolean containsPoint(Vector vector) {
            return rectPoint[0]<vector.x && vector.x<rectPoint[2] &&
                    rectPoint[1]<vector.y && vector.y<rectPoint[3];
        }
    }

    class Line {
        Vector a, b;

        Line(Vector a, Vector b) {
            if(a.greaterThen(b)) {
                this.a = a;
                this.b = b;
            } else {
                this.a = b;
                this.b = a;
            }
        }

        boolean isCross(Line other) {
            return segmentIntersection(a,b, other.a, other.b);
        }

        Vector getCrossPoint(Line other) {
            return getSegmentIntersection(a,b, other.a, other.b);
        }

        boolean isInvolved(int x, int y) {
            return isInvolved(new Vector(x,y));
        }

        boolean isInvolved(Vector v) {
            return isBoundingRectangle(v,a,b) && pointToLine(v,a,b)==0;
        }

        @Override
        public String toString() {
            return "["+a+">>"+b+"]";
        }
    }

    class Vector {
        private double x,y;
        Vector() {
            this.x = 0;
            this.y = 0;
        }
        Vector(double x, double y) {
            this.x = x;
            this.y = y;
        }
        //a+b
        Vector plus(Vector b) {
            return new Vector(x+b.x, y+b.y);
        }
        //a-b
        Vector minus(Vector b) {
            return new Vector(x-b.x, y-b.y);
        }
        //a\*r
        Vector mul(double r) {
            return new Vector(x*r, y*r);
        }
        //a/r
        Vector div(double r) {
            return new Vector(x/r, y/r);
        }
        //대소비교
        boolean greaterThen(Vector b) {
            return this.x != b.x ? this.x < b.x : this.y < b.y;
        }
        //a dot b(inner product)
        double dot(Vector b) {
            return x*b.x+y*b.y;
        }
        //a cross b(outer product)
        double cross(Vector b) {
            return b.y*x-b.x*y;
        }
        //vector's length or size
        double norm() {
            return Math.sqrt(x*x+y*y);
        }
        //unit vector
        Vector normalize() {
            return new Vector(x/norm(), y/norm());
        }
        //projection
        Vector project(Vector b) {
            Vector r = b.normalize();
            return r.mul(r.dot(this));
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Vector) {
                Vector o = (Vector) obj;
                return o.x == this.x && o.y == this.y;
            }
            return false;
        }

        @Override
        public String toString() {
            return x+","+y;
        }
    }

    //원점에서 벡터 b가 벡터 a의 반시계 방향이면 양수, 시계방향이면 음수, 평행이면 0을 반환함
    static double ccw(Vector a, Vector b) {
        return a.cross(b);
    }
    //점p를 기준으로 방향 계산
    static double ccw(Vector p, Vector a, Vector b) {
        return ccw(a.minus(p), b.minus(p));
    }

    //(a,b)를 포함하는 선과 (c,d)를 포함하는 선의 교점을 x에 반환함
    boolean lineIntersection(Vector a, Vector b, Vector c, Vector d) {
        double det = b.minus(a).cross(d.minus(c));
        return Math.abs(det) != 0;
    }

    Vector getLineIntersection(Vector a, Vector b, Vector c, Vector d) {
        double det = b.minus(a).cross(d.minus(c));
        double p = c.minus(a).cross(d.minus(c))/det;
        return a.plus(b.minus(a).mul(p));
    }

    //(a,b)를 지나는 선분과 (c,d)를 지나는 선분이 평행할 때 한점에서 겹치는지 확인함
    boolean parallelSegments(Vector a, Vector b, Vector c, Vector d) {
        if(a.greaterThen(b)) {
            Vector tmp = a;
            a = b;
            b = tmp;
        }
        if(c.greaterThen(d)) {
            Vector tmp = c;
            c = d;
            d = tmp;
        }
        //일직선상에 없거나 겹치지 않는 경우 리턴
        if(ccw(a,b,c) != 0 || c.greaterThen(b) || a.greaterThen(d)) {
            return false;
        }
        return true;
    }

    Vector getParallelSegments(Vector a, Vector b, Vector c, Vector d) {
        if(a.greaterThen(b)) {
            Vector tmp = a;
            a = b;
            b = tmp;
        }
        if(c.greaterThen(d)) {
            Vector tmp = c;
            c = d;
            d = tmp;
        }
        if(c.greaterThen(a)) {
            return c;
        } else {
            return a;
        }
    }
    //세점이 일직선상에 있는지 여부 반환
    boolean isBoundingRectangle(Vector p, Vector a, Vector b) {
        if(a.greaterThen(b)) {
            Vector tmp = a;
            a = b;
            b = tmp;
        }
        return p.equals(a) || p.equals(b) || (p.greaterThen(a) && b.greaterThen(p));
    }
    //(a,b)선분과 (c,d)선분의 교점을 p에 반환함, 두선분이 교차하지 않을 경우 false 반환
    boolean segmentIntersection(Vector a, Vector b, Vector c, Vector d) {
        if(!lineIntersection(a,b,c,d)) {
            return parallelSegments(a,b,c,d);
        }
        Vector p = getSegmentIntersection(a,b,c,d);
        return isBoundingRectangle(p,a,b) && isBoundingRectangle(p,c,d);
    }
    Vector getSegmentIntersection(Vector a, Vector b, Vector c, Vector d) {
        if(!lineIntersection(a,b,c,d)) {
            return getParallelSegments(a,b,c,d);
        } else {
            return getLineIntersection(a,b,c,d);
        }
    }
    //선분과 점 사이의 수선을 구함
    Vector prependicularFoot(Vector p, Vector a, Vector b){
        return a.plus((p.minus(a)).project(b.minus(a)));
    }
    //점 p와 (a,b)간의 거리 계산
    double pointToLine(Vector p, Vector a, Vector b){
        return (p.minus(prependicularFoot(p,a,b))).norm();
    }

    public static void main(String[] args) {
        new Solution().solution(new int[][]{{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}},1,3,7,8);
    }
}
