package projet.m2.back.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import projet.m2.back.constant.Constant;
import projet.m2.back.entity.Board;
import projet.m2.back.service.interfaces.IBoardService;

@RestController
@CrossOrigin
public class BoardController {
    @Autowired
    private IBoardService boardService;

    @GetMapping("/board")
    public ResponseEntity sendBoard(){
        Board board = boardService.getBoardByName(Constant.boardName);
        JSONObject jsonResponce = new JSONObject();
        jsonResponce.put("board", board.getBoard());
        return ResponseEntity.status(200).contentType(MediaType.valueOf(Constant.MEDIATYPE_JSON)).body(jsonResponce);
    }
}
