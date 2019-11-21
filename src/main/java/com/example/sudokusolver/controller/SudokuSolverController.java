package com.example.sudokusolver.controller;

import com.example.sudokusolver.sudoku.SudokuSolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SudokuSolverController {

    @RequestMapping(value = "/solve")
    public ResponseEntity solve(@RequestParam(value = "input") final String input) {

        if (input.length() != 81) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Input should be 81 characters long");
        }

        if (input.matches("[0-9]+")) {
            return ResponseEntity.ok(SudokuSolver.solveSudokuWithBacktracking(input));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Input should contain only numbers");
        }

    }
}
