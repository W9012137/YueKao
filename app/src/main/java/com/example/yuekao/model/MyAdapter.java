package com.example.yuekao.model;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuekao.R;
import com.example.yuekao.model.bean.GoodsBean;
import com.example.yuekao.model.bean.Count;
import com.example.yuekao.view.cus.AddView;
import com.example.yuekao.view.cus.Select;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by 王有道 on 2018/4/7.
 */

public class MyAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<GoodsBean> groupList;
    private List<List<GoodsBean.ListBean>> childList;
    public MyAdapter(Context context, List<GoodsBean> groupList, List<List<GoodsBean.ListBean>> childList) {
        this.context =context;
        this.groupList = groupList;
        this.childList = childList;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childList.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childList.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        final GroupViewHolder holder;
        if (view == null) {
            holder = new GroupViewHolder();
            view = view.inflate(context, R.layout.group_layout, null);
            holder.cbGroup = view.findViewById(R.id.cb_parent);
            holder.tv_number = view.findViewById(R.id.tv_number);
            view.setTag(holder);
        } else {
            holder = (GroupViewHolder) view.getTag();
        }
        final GoodsBean dataBean = groupList.get(i);
        holder.cbGroup.setChecked(dataBean.isChecked());
//        holder.tv_number.setText(dataBean.getTitle());
        holder.tv_number.setText(dataBean.getSellerName());
        //一级checkbox
        holder.cbGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean.setChecked(holder.cbGroup.isChecked());
                changeChildCbState(i, holder.cbGroup.isChecked());
                EventBus.getDefault().post(compute());
                changeAllCbState(isAllGroupCbSelected());
                notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        final ChildViewHolder holder;
        if (view == null) {
            holder = new ChildViewHolder();
            view = view.inflate(context,R.layout.child_layout, null);
            holder.cbChild = view.findViewById(R.id.cb_child);
            holder.tv_tel = view.findViewById(R.id.tv_tel);
//            holder.tv_content = view.findViewById(R.id.tv_content);
//            holder.tv_time = view.findViewById(R.id.tv_time);
//            holder.imgIcon = view.findViewById(R.id.img_icon);
            holder.draweeView = (SimpleDraweeView) view.findViewById(R.id.my_image_view);

            holder.tv_price = view.findViewById(R.id.tv_pri);
//            holder.iv_add = view.findViewById(R.id.iv_add);
//            holder.iv_del = view.findViewById(R.id.iv_del);
//            holder.tv_num = view.findViewById(R.id.tv_num);
            holder.adv = view.findViewById(R.id.adv_main);
            view.setTag(holder);
        } else {
            holder = (ChildViewHolder) view.getTag();
        }
        final GoodsBean.ListBean datasBean = childList.get(i).get(i1);

        holder.cbChild.setChecked(datasBean.isChecked());
//        holder.tv_tel.setText(datasBean.getType_name());
        holder.tv_tel.setText(datasBean.getTitle());
//        holder.tv_content.setText(datasBean.getMsg());
//        holder.tv_time.setText(datasBean.getAdd_time());
        holder.tv_price.setText("￥"+datasBean.getPrice() );
        holder.adv.setNumber(datasBean.getNum());
//        holder.tv_num.setText(datasBean.getNum() + "");
        String images = datasBean.getImages().trim();
        String[] split = images.split("[|]");
        holder.draweeView.setImageURI(split[0]);

//        Glide.with(context).load(split[0]).into(holder.draweeView);

        //二级checkbox
        holder.cbChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置该条目对象里的checked属性值
                datasBean.setChecked(holder.cbChild.isChecked());
                Count count = compute();
                EventBus.getDefault().post(count);

                if (holder.cbChild.isChecked()) {
                    //当前checkbox是选中状态
                    if (isAllChildCbSelected(i)) {
                        changGroupCbState(i, true);
                        changeAllCbState(isAllGroupCbSelected());
                    }
                } else {
                    changGroupCbState(i, false);
                    changeAllCbState(isAllGroupCbSelected());
                }
                notifyDataSetChanged();
            }


        });
        holder.adv.setOnAddDelClickListener(new AddView.OnAddDelClickListener() {
            @Override
            public void onAddClick(View v) {
                Log.i(TAG, "onAddClick: 执行");
                int origin = holder.adv.getNumber();
                origin++;
//                holder.adv.setNumber(origin);
                int num = datasBean.getNum();
                num++;
                holder.adv.setNumber(num);
                datasBean.setNum(num);
                if (holder.cbChild.isChecked()) {
                    EventBus.getDefault().post(compute());
                }
            }

            @Override
            public void onDelClick(View v) {
                int origin = holder.adv.getNumber();
//                int num = datasBean.getNum();

                origin--;
                if (origin == 0) {
                    Toast.makeText(context,"最小数量为1",Toast.LENGTH_SHORT).show();
                    return ;
                }
                holder.adv.setNumber(origin);
                datasBean.setNum(origin);
                if (holder.cbChild.isChecked()) {

                    EventBus.getDefault().post(compute());
                }

            }
        });


        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
    class GroupViewHolder {
        CheckBox cbGroup;
        TextView tv_number;
    }

    class ChildViewHolder {
        CheckBox cbChild;
        TextView tv_tel;
        TextView tv_content;
        TextView tv_time;
        //        ImageView imgIcon;
        SimpleDraweeView draweeView;
        TextView tv_price;
        TextView tv_del;
        ImageView iv_del;
        ImageView iv_add;
        TextView tv_num;
        AddView adv;
    }
    /**
     * 改变全选的状态
     *
     * @param flag
     */
    private void changeAllCbState(boolean flag) {
        Select select = new Select();
        select.setChecked(flag);
        EventBus.getDefault().post(select);
    }
    /**
     * 改变一级列表checkbox状态
     *
     * @param groupPosition
     */
    private void changGroupCbState(int groupPosition, boolean flag) {
//        GoosBean.DataBean dataBean = groupList.get(groupPosition);
        GoodsBean dataBean = groupList.get(groupPosition);

        dataBean.setChecked(flag);
    }

    /**
     * 改变二级列表checkbox状态
     *
     * @param groupPosition
     * @param flag
     */
    private void changeChildCbState(int groupPosition, boolean flag) {
        List<GoodsBean.ListBean> datasBeen = childList.get(groupPosition);

        for (int i = 0; i < datasBeen.size(); i++) {
            GoodsBean.ListBean datasBean = datasBeen.get(i);
            datasBean.setChecked(flag);
        }
    }
    /**
     * 判断一级列表是否全部选中
     *
     * @return
     */
    private boolean isAllGroupCbSelected() {
        for (int i = 0; i < groupList.size(); i++) {
            GoodsBean dataBean = groupList.get(i);

            if (!dataBean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断二级列表是否全部选中
     *
     * @param groupPosition
     * @return
     */
    private boolean isAllChildCbSelected(int groupPosition) {
        List<GoodsBean.ListBean> datasBeen = childList.get(groupPosition);

        for (int i = 0; i < datasBeen.size(); i++) {
            GoodsBean.ListBean datasBean = datasBeen.get(i);

            if (!datasBean.isChecked()) {
                return false;
            }
        }
        return true;
    }
    /**
     * 计算列表中，选中的钱和数量
     */
    private Count compute() {
        int count = 0;
        int price = 0;
        for (int i = 0; i < childList.size(); i++) {
            List<GoodsBean.ListBean> datasBeen = childList.get(i);

            for (int j = 0; j < datasBeen.size(); j++) {
                GoodsBean.ListBean datasBean = datasBeen.get(j);

                if (datasBean.isChecked()) {
                    price += datasBean.getNum() * datasBean.getPrice();
                    count += datasBean.getNum();
                }
            }
        }
        Count priceAndCountEvent = new Count();
        priceAndCountEvent.setCount(count);
        priceAndCountEvent.setPrice(price);
        return priceAndCountEvent;
    }
    /**
     * 设置全选、反选
     *
     * @param flag
     */
    public void changeAllListCbState(boolean flag) {
        for (int i = 0; i < groupList.size(); i++) {
            changGroupCbState(i, flag);
            changeChildCbState(i, flag);
        }
        EventBus.getDefault().post(compute());
        notifyDataSetChanged();
    }

}
