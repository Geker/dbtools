package cn.db.bean;

public class Dept {
    private long Deptno;
    private String Dname;
    private String Loc;

    public long getDeptno() {
        return Deptno;
    }

    public void setDeptno(long deptno) {
        Deptno = deptno;
    }

    public String getDname() {
        return Dname;
    }

    public void setDname(String dname) {
        Dname = dname;
    }

    public String getLoc() {
        return Loc;
    }

    public void setLoc(String loc) {
        Loc = loc;
    }

    @Override
    public String toString() {
        return "Dept [Deptno=" + Deptno + ", Dname=" + Dname + ", Loc=" + Loc + "]";
    }


}
