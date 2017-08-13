package com.zhangpeng.design;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/8/25.
 */
class MyAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView textView;
        private View root;
        public ViewHolder(View root) {
            super(root);
            image = (ImageView) root.findViewById(R.id.image);
            textView = (TextView)root.findViewById(R.id.text);
        }

        public ImageView getContent() {
            return image;
        }
        public TextView getTextView(){
            return textView;
        }


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,null);
        ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        int cd = images[position];
        String tx = text[position];
        vh.getContent().setImageResource(cd);
        vh.getTextView().setText(tx);
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(text[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    private int[] images = new int[]{R.drawable.air,R.drawable.tv,R.drawable.kettel,
            R.drawable.light,R.drawable.control,R.drawable.music};
    private String[] text = new String[]{
            "空调","电视","热水壶","台灯","监控","音乐"
    };


    //设置监听事件
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    //define interface设置监听接口
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , String data);
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,(String)v.getTag());
        }
    }
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
