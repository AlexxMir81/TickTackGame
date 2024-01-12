package com.example.ticktackgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    private int step = 1;
    private int size = 3;
    TextView resultMessage;
    String[][] grid;
    GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TextView textView = findViewById(R.id.Mode_text);
        String str = getIntent().getStringExtra("mode");
        textView.setText(str);
        resultMessage = findViewById(R.id.Winner_text);
        Button backButton = findViewById(R.id.Back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackMainActivity(v);
            }
        });

        Button resetButton = findViewById(R.id.Reset_btn);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultMessage.setText("");
                ClearGrid();
            }
        });

        gridLayout = findViewById(R.id.Grid_content);//new GridLayout(this);

        for (int i=0; i<Math.pow(size,2); i++){
            Button button = new Button(this);
            button.setText("");
            button.setTextSize(40);
            gridLayout.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if(button.getText()=="") {
                       if (step % 2 == 0) {
                           button.setText("X");
                       } else {
                           button.setText("O");
                       }
                        step++;
                        if(!getResult()){
                           if(str.equals(getString(R.string.mode2))) {
                               computerStep();
                           }
                            if(str.equals(getString(R.string.mode3))) {
                                computerStepHard();
                            }
                        }
                    }
                }
            });
        }
        gridLayout.setBackgroundColor(Color.BLACK);
    }

    public void computerStep() {
        for (int i = 0; i < gridLayout.getRowCount(); i++) {
            for (int j = 0; j < gridLayout.getColumnCount(); j++) {
                if (grid[i][j].equals("")) {
                    if (step % 2 == 0) {
                        View childView = gridLayout.getChildAt(j * gridLayout.getColumnCount() + i);
                        Button button = (Button) childView;
                        button.setText("X");
                        getResult();
                        step++;
                        return;
                    }
                }
            }
        }
    }
    public void computerStepHard() {
        boolean type = false;
     //   grid= fillingGrid(size,size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0) {
                    if (!grid[i][j].equals("") && grid[i][j].equals(grid[i+1][j]) && grid[i + 2][j].equals("")) {
                        makeStep(i + 2, j);
                        return;
                    }
                    if (!grid[i][j].equals("") && grid[i][j].equals(grid[i+2][j]) && grid[i + 1][j].equals("")) {
                        makeStep(i + 1, j);
                        return;
                    }
                }
                if (i == size - 1) {
                    if (!grid[i][j].equals("") && grid[i][j].equals(grid[i-1][j]) && grid[i - 1][j].equals("")) {
                        makeStep(i - 1, j);
                        return;
                    }
                }
                if (i < size - 1 && i > 0) {
                    if (!grid[i][j].equals("") && grid[i][j].equals(grid[i+1][j]) && grid[i - 1][j].equals("")) {
                        makeStep(i - 1, j);
                        return;
                    }
                }
                if (j == 0) {
                    if (!grid[i][j].equals("") && grid[i][j].equals(grid[i][j + 1]) && grid[i][j + 2].equals("")) {
                        makeStep(i, j + 2);
                        return;
                    }
                    if (!grid[i][j].equals("") && grid[i][j].equals(grid[i][j + 2]) && grid[i][j + 1].equals("")) {
                        makeStep(i, j + 1);
                        return;
                    }
                }
                if (j == size - 1) {
                    if (!grid[i][j].equals("") && grid[i][j].equals(grid[i][j - 1]) && grid[i][j - 2].equals("")) {
                        makeStep(i,j - 2);
                        return;
                    }
                }
                if (j < size - 1 && j > 0) {
                    if (!grid[i][j].equals("") && grid[i][j].equals(grid[i][j + 1]) && grid[i][j - 1].equals("")) {
                        makeStep(i, j - 1);
                        return;
                    }
                }
                if (i == 0 && i==j) {
                    if (!grid[i][j].equals("") && grid[i][j].equals(grid[i+1][j+1]) && grid[i + 2][j+2].equals("")) {
                        makeStep(i + 2, j+2);
                        return;
                    }
                }
                if (i == size - 1 && i==j) {
                    if (!grid[i][j].equals("") && grid[i][j].equals(grid[i-1][j-1]) && grid[i - 2][j-2].equals("")) {
                        makeStep(i - 1, j-1);
                        return;
                    }
                }
                if (i == 0 && j==size-1) {
                    if (!grid[i][j].equals("") && grid[i][j].equals(grid[i+1][j-1]) && grid[i + 2][j-2].equals("")) {
                        makeStep(i + 2, j - 2);
                        return;
                    }
                }
                if (i == size - 1 && j==0) {
                    if (!grid[i][j].equals("") && grid[i][j].equals(grid[i-1][j+1]) && grid[i - 2][j + 1].equals("")) {
                        makeStep(i - 2, j + 1);
                        return;
                    }
                }
                if (i < size - 1 && i > 0 && i==j) {
                    if (!grid[i][j].equals("") && grid[i][j].equals(grid[i+1][j+1]) && grid[i - 1][j-1].equals("")) {
                        makeStep(i - 1, j-1);
                        return;
                    }
                }
                if (i>0 && i<size-1 && j>0 && j<size-1 && grid[i][j].equals("")){
                    makeStep(i, j);
                    return;
                }
            }
        }
        if (!type) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (grid[i][j].equals("")){
                        makeStep(i, j);
                        return;
                    }
                }
            }
        }
    }
    public void makeStep(int row, int col){
        if (step % 2 == 0) {
            View childView = gridLayout.getChildAt(col * size + row);
            Button button = (Button) childView;
            button.setText("X");
            step++;
            getResult();
        }
    }
    public void ClearGrid () {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View childView = gridLayout.getChildAt(i);
            Button button = (Button) childView;
            button.setText("");
            step = 1;
        }
    }
    public String[][] fillingGrid ( int row, int col){
        String[][] grid = new String[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                View childView = gridLayout.getChildAt(j * gridLayout.getColumnCount() + i);
                Button button = (Button) childView;
                grid[i][j] = button.getText().toString();
            }
        }
        return grid;
    }
    public boolean CheckWinner () {
        grid = fillingGrid(size, size);
        for (int i = 0; i < size; i++) {
            if (!grid[i][0].equals("") && grid[i][0].equals(grid[i][1]) && grid[i][0].equals(grid[i][2])) {
                return true;
            }
            if (!grid[0][i].equals("") && grid[0][i].equals(grid[1][i]) && grid[0][i].equals(grid[2][i])) {
                return true;
            }
            if (!grid[0][0].equals("") && grid[0][0].equals(grid[1][1]) && grid[0][0].equals(grid[2][2])) {
                return true;
            }
            if (!grid[0][2].equals("") && grid[0][2].equals(grid[1][1]) && grid[0][2].equals(grid[2][0])) {
                return true;
            }
        }
        return false;
    }

    public boolean getResult() {
        if (CheckWinner()) {
            if (step % 2 == 0) {
                resultMessage.setText("Победитель: O");
                message("Победитель: O",Gravity.CENTER);
            } else {
                resultMessage.setText("Победитель: X");
                message("Победитель: X",Gravity.CENTER);
            }
            //ClearGrid();
            step = 1;
            return  true;
        } else if (!canStep()){
            resultMessage.setText("Ничья");
            message("Ничья",Gravity.CENTER);
            return true;
        }
        return false;
    }

    public void message(String text, int gravity){
        Toast toast = Toast.makeText(GameActivity.this, text, Toast.LENGTH_LONG);
        toast.setGravity(gravity, 0,0);
        toast.show();
    }
    public boolean canStep(){
        boolean result = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(grid[i][j].equals("")){
                    result = true;
                    return result;
                }
            }
        }
        return result;
    }
    public void BackMainActivity(View view) {
        Intent intent = new Intent();
        setResult(200,intent);
        finish();
    }
}
