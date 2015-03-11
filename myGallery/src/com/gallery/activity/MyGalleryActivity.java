package com.gallery.activity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.app.gallery.R;
import com.select.image.MainActivity;
import com.sqlite.db.mSqlite;

public class MyGalleryActivity extends Activity {

	private mSqlite m_Sqlite;
	private List<String> imageList;
	private String userIdNumString;
	Uri imageUri;
	String imagePathString;
	Intent imageSelectIntent;
	int REQUEST_CODE;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);

		// 初始化数据库
		m_Sqlite = new mSqlite(this);

		// 初始化人员id
		userIdNumString = (String) this.getIntent().getSerializableExtra(
				"userIdNumString");

		// 初始化资源标识符
		imageUri = null;

		initImageItems();

		// 初始化意图
		imageSelectIntent = new Intent();
		imageSelectIntent.setComponent(new ComponentName(
				MyGalleryActivity.this, MainActivity.class));

		// 初始化意图返回值
		REQUEST_CODE = 0;
	}

	public void initImageItems() {

		// 初始化图像列表
		imageList = new ArrayList<String>();

		// 获取图像列表
		imageList = m_Sqlite.getPicPath(userIdNumString);

		GridView gridview = (GridView) findViewById(R.id.photo_gridview);

		// 添加并且显示
		gridview.setAdapter(new PhotoWallAdapter(imageList, this));
		// 添加消息处理
		gridview.setOnItemClickListener(new ItemClickListener());
	}

	// 当AdapterView被单击(触摸屏或者键盘)，则返回的Item单击事件
	class ItemClickListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0,// The AdapterView where the
													// click happened
				View arg1,// The view within the AdapterView that was clicked
				int arg2,// The position of the view in the adapter
				long arg3// The row id of the item that was clicked
		) {
			REQUEST_CODE = 2;
			imageSelectIntent.putExtra("imageString", imageList.get(arg2)
					.toString());
			imageSelectIntent.putExtra("userIdNumString", userIdNumString);
			startActivityForResult(imageSelectIntent, REQUEST_CODE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		// 添加菜单
		int base = Menu.FIRST;
		int i = 0;

		menu.add(base, base + (i++), Menu.NONE, "添加图片");
		menu.add(base, base + (i++), Menu.NONE, "修改密码");
		menu.add(base, base + (i++), Menu.NONE, "添加用户");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// 菜单选择
		switch (item.getItemId()) {
		case 1: // 添加图片
			showPicturePicker(MyGalleryActivity.this);
			break;
		case 2: // 修改密码
			final LayoutInflater passwordInflater = getLayoutInflater();
			final View passwordLayout = passwordInflater.inflate(
					R.layout.password, null);
			final Button passwordOkButton = (Button) passwordLayout
					.findViewById(R.id.passwordOk_button);
			final Button passwordCancelButton = (Button) passwordLayout
					.findViewById(R.id.passwordCancel_button);
			final AlertDialog.Builder passwordBuilder = new AlertDialog.Builder(
					MyGalleryActivity.this).setTitle("设置密码 : ").setView(
					passwordLayout);
			final Dialog passwordDialog = passwordBuilder.show();

			passwordOkButton.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					boolean successFlag = false;
					String passwordOneString = ((EditText) passwordLayout
							.findViewById(R.id.passwordOne_edit)).getText()
							.toString().trim();
					String passwordTwoString = ((EditText) passwordLayout
							.findViewById(R.id.passwordTwo_edit)).getText()
							.toString().trim();

					if (true == passwordOneString.equals(passwordTwoString)) {
						successFlag = m_Sqlite.modPassword(userIdNumString,
								passwordOneString);
						if (true == successFlag) {
							Toast.makeText(getApplicationContext(), "修改成功",
									Toast.LENGTH_SHORT).show();
							passwordDialog.dismiss();
						} else {
							Toast.makeText(getApplicationContext(), "修改失败",
									Toast.LENGTH_SHORT).show();
							passwordDialog.dismiss();
						}
					} else {
						Toast.makeText(getApplicationContext(), "两次输入不一致",
								Toast.LENGTH_SHORT).show();
					}
				}
			});

			passwordCancelButton.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					passwordDialog.dismiss();
				}
			});
			break;
		case 3: // 添加用户
			final LayoutInflater newUserInflater = getLayoutInflater();
			final View newUserLayout = newUserInflater.inflate(
					R.layout.newuser, null);
			final Button newUserOkButton = (Button) newUserLayout
					.findViewById(R.id.passwordOk_button);
			final Button newUserCancelButton = (Button) newUserLayout
					.findViewById(R.id.passwordCancel_button);
			final AlertDialog.Builder newUserBuilder = new AlertDialog.Builder(
					MyGalleryActivity.this).setTitle("添加用户 : ").setView(
					newUserLayout);
			final Dialog newUserDialog = newUserBuilder.show();

			newUserOkButton.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					boolean successFlag = false;
					String nameNewString = ((EditText) newUserLayout
							.findViewById(R.id.nameNew_edit)).getText()
							.toString().trim();
					String passwordNewString = ((EditText) newUserLayout
							.findViewById(R.id.passwordNew_edit)).getText()
							.toString().trim();

					successFlag = m_Sqlite.insertUser(nameNewString,
							passwordNewString);
					if (true == successFlag) {
						Toast.makeText(getApplicationContext(), "添加成功",
								Toast.LENGTH_SHORT).show();
						newUserDialog.dismiss();
					} else {
						Toast.makeText(getApplicationContext(), "添加失败(用户名已存在)",
								Toast.LENGTH_SHORT).show();
						newUserDialog.dismiss();
					}
				}
			});

			newUserCancelButton.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					newUserDialog.dismiss();
				}
			});
			break;
		default:
			Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT)
					.show();
			break;
		}
		return true;
	}

	public void showPicturePicker(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("图片来源");
		builder.setNegativeButton("取消", null);
		builder.setItems(new String[] { "拍照", "相册" },
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case 0:
							REQUEST_CODE = 0;
							Intent openCameraIntent = new Intent(
									MediaStore.ACTION_IMAGE_CAPTURE);

							String fileName = "IMG_"
									+ new SimpleDateFormat("yyyyMMdd_HHmmss")
											.format(new Date()) + ".jpg";

							imageUri = Uri.fromFile(new File(
									Environment
											.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
									fileName));

							openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
									imageUri);

							startActivityForResult(openCameraIntent,
									REQUEST_CODE);
							break;

						case 1:
							REQUEST_CODE = 1;
							Intent openAlbumIntent = new Intent(
									Intent.ACTION_GET_CONTENT);

							openAlbumIntent
									.setDataAndType(
											MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
											"image/*");

							startActivityForResult(openAlbumIntent,
									REQUEST_CODE);
							break;

						default:
							break;
						}
					}
				});
		builder.create().show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			imagePathString = "";
			switch (requestCode) {
			case 0:
				// 拍摄照片
				imagePathString = imageUri.getPath();
				m_Sqlite.insertPicPath(userIdNumString, imagePathString);
				Toast.makeText(getApplicationContext(), imageUri.getPath(),
						Toast.LENGTH_SHORT).show();
				break;
			case 1:
				// 选择照片
				Uri originalUri = data.getData(); // 获得图片的uri
				// 这里开始的第二部分，获取图片的路径：
				String[] proj = { MediaStore.Images.Media.DATA };
				// 好像是android多媒体数据库的封装接口，具体的看Android文档
				Cursor cursor = managedQuery(originalUri, proj, null, null,
						null);
				// 按我个人理解 这个是获得用户选择的图片的索引值
				int column_index = cursor
						.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				// 将光标移至开头 ，这个很重要，不小心很容易引起越界
				cursor.moveToFirst();
				// 最后根据索引值获取图片路径
				imagePathString = cursor.getString(column_index);

				m_Sqlite.insertPicPath(userIdNumString, imagePathString);
				Toast.makeText(getApplicationContext(), imagePathString,
						Toast.LENGTH_SHORT).show();
				break;
			case 2:
				// 单击列表
				initImageItems();
				break;
			default:
				break;
			}
			initImageItems();
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (m_Sqlite != null) {
			m_Sqlite.close();
			m_Sqlite = null;
		}
	}
}