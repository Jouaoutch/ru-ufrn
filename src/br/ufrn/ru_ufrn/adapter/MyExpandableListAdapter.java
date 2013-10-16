package br.ufrn.ru_ufrn.adapter;

import br.ufrn.ru_ufrn.ImageUtil;
import br.ufrn.ru_ufrn.R;
import br.ufrn.ru_ufrn.model.Alimento;
import br.ufrn.ru_ufrn.model.Refeicao;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

	private final SparseArray<Refeicao> groups;
	public LayoutInflater inflater;
	public Activity activity;
	private ImageUtil imgutil;
	

	public MyExpandableListAdapter(Activity act, SparseArray<Refeicao> groups) {
		activity = act;
		this.groups = groups;
		inflater = act.getLayoutInflater();
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return groups.get(groupPosition).itens.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final Alimento item = (Alimento) getChild(groupPosition, childPosition);
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listrow_details, null);
		}
		TextView text = (TextView) convertView.findViewById(R.id.textView_nome);
		text.setText(item.getNome());
		TextView text2 = (TextView) convertView.findViewById(R.id.textView_descricao);
		text2.setText(item.getDescricao());		
		imgutil = new ImageUtil();
		Drawable image = null;
		if (item.getImagem().startsWith("http://")) {
			try {
				image = imgutil.getImagem(item.getImagem());
			} catch (Exception e) {
				Toast.makeText(
						this.activity,
						"Erro ao recuperar a imagem na URL: "
								+ item.getImagem(), Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
			text.setCompoundDrawables(image, null, null, null);
		}
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(activity, item.getNome(), Toast.LENGTH_SHORT).show();
			}
		});
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return groups.get(groupPosition).itens.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groups.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return groups.size();
	}

	@Override
	public void onGroupCollapsed(int groupPosition) {
		super.onGroupCollapsed(groupPosition);
	}

	@Override
	public void onGroupExpanded(int groupPosition) {
		super.onGroupExpanded(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listrow_group, null);
		}
		Refeicao group = (Refeicao) getGroup(groupPosition);
		((CheckedTextView) convertView).setText(group.getNome());
		((CheckedTextView) convertView).setChecked(isExpanded);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}
}