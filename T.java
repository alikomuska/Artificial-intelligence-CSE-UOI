import java.util.ArrayList;

class T
{
    public ArrayList<Integer> reverse(ArrayList al)
    {

        for (int i = 0, j = al.size() - 1; i < j; i++)
        {
            al.add(i, al.remove(j));
        }

        return(al);
    }
    public ArrayList<Integer> join(ArrayList ral, ArrayList ar)
    {
        ArrayList<Integer> b = new ArrayList<Integer>(ral);
        b.addAll(ar);
        return(b);
    }

    public ArrayList<ArrayList<Integer>> split(ArrayList<Integer> list,int k)
    {
        ArrayList<Integer> first = new ArrayList<Integer>();
        ArrayList<Integer> second = new ArrayList<Integer>();

        int size = list.size();

        for (int i = 0; i < k; i++)
            first.add(list.get(i));

        for (int i = k; i < size; i++)
            second.add(list.get(i));

        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        lists.add(first);
        lists.add(second);

        return(lists);
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(3);
        a.add(5);
        a.add(4);
        a.add(1);
        a.add(2);
        int k = 4;
        T temp = new T();
        ArrayList<ArrayList<Integer>> lists = temp.split(a,k);
        ArrayList<Integer> al = lists.get(0);
        ArrayList<Integer> ar = lists.get(1);

        ArrayList<Integer> ral = temp.reverse(al);
        ArrayList<Integer> b = temp.join(ral, ar);
        System.out.print(b);
    }
}