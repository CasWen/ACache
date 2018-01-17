package demo.zxg.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zxg.cache.ACache;

import org.json.JSONException;
import org.json.JSONObject;

public class ReadJsonObjectActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTv_jsonobject_original, mTv_jsonobject_res;
    private JSONObject jsonObject;
    private Button save_btn;
    private Button read_btn;
    private Button clear_btn;

    private ACache mCache;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_jsonobject);

        ininView();
        mCache = ACache.get(this);
        initData();

    }

    /**
     * 初始化控件
     */
    private void ininView(){
        mTv_jsonobject_original = (TextView) findViewById(R.id.tv_jsonobject_original);
        mTv_jsonobject_res  = (TextView) findViewById(R.id.tv_jsonobject_res);
        save_btn = (Button) findViewById(R.id.save);
        read_btn = (Button) findViewById(R.id.read);
        clear_btn = (Button) findViewById(R.id.clear);
    }

    /**
     * 初始化数据
     */

    private void initData(){
        jsonObject = new JSONObject();
        try {
            jsonObject.put("name", "李刚");
            jsonObject.put("age", 18);
            jsonObject.put("job","程序员");
            jsonObject.put("job11","程序员11");
            jsonObject.put("job22","程序员22");
            jsonObject.put("job33","程序员33");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mTv_jsonobject_original.setText(jsonObject.toString());
        saveData();
        readData();
        clearData();
    }

    @Override
    public void onClick(View view) {
        save_btn.setOnClickListener(this);
        read_btn.setOnClickListener(this);
        clear_btn.setOnClickListener(this);
    }

    /**
     * save事件
     *
     * @param
     */
    public void saveData() {

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCache.put("testJsonObject", jsonObject);
            }
        });
    }

    /**
     * 点击read事件
     *
     * @param
     */
    public void readData() {
        final JSONObject testJsonObject = mCache.getAsJSONObject("testJsonObject");
        read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (testJsonObject == null) {
                    Toast.makeText(ReadJsonObjectActivity.this, "数据为空 ...",
                                   Toast.LENGTH_SHORT).show();
                    mTv_jsonobject_res.setText(null);
                    return;
                }
                mTv_jsonobject_res.setText(testJsonObject.toString());
            }
        });

    }
    /**
     * 点击clear事件
     *
     * @param
     */
    public void clearData() {
        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCache.remove("testJsonObject");
            }
        });

    }
}
