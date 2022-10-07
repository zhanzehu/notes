package com.smartspiritnotes.notes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.smartspiritnotes.mxeditor.MXText;

public class EditNoteActivity extends Activity {

    //粗体。。
    private static final String BOLD = "<b>Bold</b><br><br>";
    //斜体。。
    private static final String ITALIT = "<i>Italic</i><br><br>";
    //下划线。。
    private static final String UNDERLINE = "<u>Underline</u><br><br>";
    //删除线
    private static final String STRIKETHROUGH = "<s>Strikethrough</s><br><br>"; // <s> or <strike> or <del>
    //序号
    private static final String BULLET = "<ul><li>asdfg</li></ul>";
    //引用
    private static final String QUOTE = "<blockquote>Quote</blockquote>";
    //链接
    private static final String LINK = "<a href=\"https://github.com/mthli/Knife\">Link</a><br><br>";
    //例子？？？整体
    private static final String EXAMPLE = BOLD + ITALIT + UNDERLINE + STRIKETHROUGH + BULLET + QUOTE + LINK;

    //NDK对象
    private MXText mxText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        //获取MXText元素,用于改变文本
        mxText = findViewById(R.id.mxText);
        // ImageGetter coming soon...
        //将EXAMPLE转换成Html
        mxText.fromHtml(EXAMPLE);
        //获取可编辑的文字长度
        mxText.setSelection(mxText.getEditableText().length());

        //设置以下子按钮
        setupBold();
        setupItalic();
        setupUnderline();
        setupStrikethrough();
        setupBullet();
        setupQuote();
        setupLink();
        setupClear();
    }


    //设置粗体按钮
    private void setupBold() {
        //获取粗体控件
        ImageButton bold = findViewById(R.id.bold);

        //设置粗体控件的监听事件
        bold.setOnClickListener(v -> {
            //精华
            mxText.bold(!mxText.contains(MXText.FORMAT_BOLD));
        });

        //设置粗体的长按的监听事件
        bold.setOnLongClickListener(v -> {
            //打印log
            Toast.makeText(EditNoteActivity.this, R.string.toast_bold, Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    private void setupItalic() {
        ImageButton italic = findViewById(R.id.italic);

        italic.setOnClickListener(v -> mxText.italic(!mxText.contains(MXText.FORMAT_ITALIC)));

        italic.setOnLongClickListener(v -> {
            Toast.makeText(EditNoteActivity.this, R.string.toast_italic, Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    private void setupUnderline() {
        ImageButton underline = findViewById(R.id.underline);

        underline.setOnClickListener(v -> mxText.underline(!mxText.contains(MXText.FORMAT_UNDERLINED)));

        underline.setOnLongClickListener(v -> {
            Toast.makeText(EditNoteActivity.this, R.string.toast_underline, Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    private void setupStrikethrough() {
        ImageButton strikethrough = findViewById(R.id.strikethrough);

        strikethrough.setOnClickListener(v -> mxText.strikethrough(!mxText.contains(MXText.FORMAT_STRIKETHROUGH)));

        strikethrough.setOnLongClickListener(v -> {
            Toast.makeText(EditNoteActivity.this, R.string.toast_strikethrough, Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    private void setupBullet() {
        ImageButton bullet = findViewById(R.id.bullet);

        bullet.setOnClickListener(v -> mxText.bullet(!mxText.contains(MXText.FORMAT_BULLET)));


        bullet.setOnLongClickListener(v -> {
            Toast.makeText(EditNoteActivity.this, R.string.toast_bullet, Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    private void setupQuote() {
        ImageButton quote = findViewById(R.id.quote);

        quote.setOnClickListener(v -> mxText.quote(!mxText.contains(mxText.FORMAT_QUOTE)));

        quote.setOnLongClickListener(v -> {
            Toast.makeText(EditNoteActivity.this, R.string.toast_quote, Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    private void setupLink() {
        ImageButton link = findViewById(R.id.link);

        link.setOnClickListener(v -> {
            //进入链接
           // showLinkDialog();
            //保存
            Log.d("ttsl",EXAMPLE);

        });

        link.setOnLongClickListener(v -> {
            Toast.makeText(EditNoteActivity.this, R.string.toast_insert_link, Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    private void setupClear() {
        ImageButton clear = findViewById(R.id.clear);

        clear.setOnClickListener(v -> {
            //清除格式
            mxText.clearFormats();
        });

        clear.setOnLongClickListener(v -> {
            Toast.makeText(EditNoteActivity.this, R.string.toast_format_clear, Toast.LENGTH_SHORT).show();
            return true;
        });
    }

  /*  private void showLinkDialog() {
        //获取开头指针
        final int start = mxText.getSelectionStart();
        //获取最后指针
        final int end = mxText.getSelectionEnd();

        //对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        //对话框样式
        View view = getLayoutInflater().inflate(R.layout.dialog_link, null, false);
        //获取编辑控件
        final EditText editText = (EditText) view.findViewById(R.id.edit);
        builder.setView(view);
        builder.setTitle(R.string.dialog_title);

        //设置点击ok后的监听事件
        builder.setPositiveButton(R.string.dialog_button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String link = editText.getText().toString().trim();
                if (TextUtils.isEmpty(link)) {
                    return;
                }

                // When KnifeText lose focus, use this method
                knife.link(link, start, end);
            }
        });

        builder.setNegativeButton(R.string.dialog_button_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // DO NOTHING HERE
            }
        });

        builder.create().show();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.undo:
                 mxText.undo();
                break;
            case R.id.redo:
                mxText.redo();
                break;
            case R.id.github:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.app_repo)));
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }
}