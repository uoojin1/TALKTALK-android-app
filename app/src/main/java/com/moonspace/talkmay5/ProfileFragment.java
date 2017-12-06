package com.moonspace.talkmay5;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by eugenemoon on 2016. 5. 15..
 */
public class ProfileFragment extends Fragment {

    static int profilePosition;
    static TextView textView;

    public ProfileFragment() {
        System.out.println("Profile Fragment Constructor");
    }
    /* public static ProfileFragment newInstance(int position) {

        System.out.println("Profile Fragment new Instance");
        profilePosition = position;
        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        args.putInt("position",position);
        fragment.setArguments(args);

        return fragment;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.profilefragment, container, false);

        Intent intent = getActivity().getIntent();
        int positionNumber = intent.getIntExtra("pos", 0);
        ArrayList<String> name = intent.getStringArrayListExtra("Name");
        ArrayList<String> role = intent.getStringArrayListExtra("Role");
        ArrayList<Integer> profpics = intent.getIntegerArrayListExtra("profilePics");

        ImageView profileimg = (ImageView)rootView.findViewById(R.id.profilePicture);
        profileimg.setImageResource(profpics.get(positionNumber));

        //text for name
        TextView tex_name = (TextView)rootView.findViewById(R.id.name1);
        tex_name.setText(name.get(positionNumber));
        tex_name.setTextColor(Color.BLACK);
        tex_name.setTextSize(14);
        //tex_name.setTextColor(getResources().getColor(R.color.colorAccent));
        //text for position
        TextView tex_pos = (TextView)rootView.findViewById(R.id.position1);
        tex_pos.setText(role.get(positionNumber));
        tex_pos.setTextColor(Color.BLACK);
        //tex_pos.setTextColor(getResources().getColor(R.color.colorAccent));

        TextView tex_introduce = (TextView)rootView.findViewById(R.id.introduce1);
        tex_introduce.setTextColor(Color.BLACK);
        if(positionNumber == 0)
            tex_introduce.setText("나대는것 하나는 정말 세계 1인자이다, 이름은 김범석." );
        else if(positionNumber == 1)
            tex_introduce.setText("꽃다운 신입생 시절, 하영이는 주변에서 말림에도 불구하고 흑역사 생성기 톡톡가왕에 나갔다가 완전 망하고 결국 기권하고만다. 그 일로 톡톡 내에서 잘ㄱ하영이라는 별명도 얻게된다. 그리고 학년이 끝나갈 무렵, 그녀는  톡톡의 현 메인작가, 그 당시 인턴이였던 승민이를 통해 여자dj면접을 신청하고 얼굴에 철판을 깔고 인터뷰를 보게된다. 중간중간 남자dj 범석이의 권력남용으로 인해 힘들어 하지만 결국 하영이는 여자dj이가 되고마는데.." );
        else if(positionNumber == 2)
            tex_introduce.setText("요리의 제왕. 그녀의 이름은 배채연." );
        else if(positionNumber == 3)
            tex_introduce.setText("톡톡 앱 개발자 문유진이라고 합니다. 작년엔 디제이였다가 이번엔 IT를 맡게 됐네요." );
        else if(positionNumber == 4)
            tex_introduce.setText("2012년  순수하던 신입생 윤미는 선배들의 꼬드김에 톡톡에 발을 담그고 만다. 2년간 여자 디제이라는 이름아래 온갖 수모를 겪은 윤미는 트라우마로 인해 잠적하고 말았다. 그 후 2016년, 윤미가 피디로 돌아왔다!?!? 이 복수극의 결말은....?" );
        else if(positionNumber == 5)
            tex_introduce.setText("그의 이름은 김동훈. 전설이다." );
        else if(positionNumber == 6)
            tex_introduce.setText("조용한 학교생활을 추구하며 날이 갈 수록 학교에서도 보이지 않고 핀치에서만 서식하던 예슬, 그녀가 다시 세상으로 나와 톡톡의 디제이 자리에 지원한다. 들려온 소식은 뜻밖의 결과...! 디제이가 아닌 작가로 뽑힌것이다. 하지만 막내작가 자리의 실체는 시다바리였는데..." );
        else if(positionNumber == 7)
            tex_introduce.setText("첫인상하면 세보이는 인상, 차가워 보이는 이미지, 차도녀 등등을 떠오르게하는 재원이. 하지만 사실 그녀의 속은 여리고 순수한, 아주 가끔은 또라이끼가 분출되는 여대생이다. 소심한 성격탓에 뭔가 해보는 것을 두려워하던 그녀가 톡톡의 작가로 첫 걸음을 내딛게 되는데..." );
        else if(positionNumber == 8)
            tex_introduce.setText("낯을 심하게 가리지만 조금 안면이 생기면 남녀노소 거리낌 없이 대하는 그녀 김혜령은 캐네디언 2세, 즉 바나나이다. 하지만 그녀는 사실상 한국 은어와 비속어를 완전한 한국인보다 더 잘 사용하고 응용할 수 있는 캐네디언이였고 그런 그녀가 톡톡의 마케팅을 맡게되는데...");
        else if(positionNumber == 9)
            tex_introduce.setText("처음 만난사람도 남녀노소 거침없이 대하는 그녀 정승민! 순진한 시골소녀였던 그녀는 2015년, 유티에 들어오게 된다! 대학의 꽃 엠티에서 그녀는 술을 아무리 마셔도 죽지 않아 유티좀비라는 별명을 얻게 되고... 묻혀야 할 그 사실이 톡톡으로 인해 온 세상에 까발려지고 만다. 2016, 신입생 신분에서 벗어난 그녀. 작년의 복수를 위해 윤미와 손을 잡고 톡톡 메인 작가가 되는데..." );
        else if(positionNumber == 10)
            tex_introduce.setText("유진 디벨로퍼의 애완묘 설월" );
        else
            tex_introduce.setText("보들보들한 두부랍니다" );

        return rootView;
    }

}
