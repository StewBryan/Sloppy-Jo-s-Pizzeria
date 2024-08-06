package com.techelevator.controller;

import javax.validation.Valid;

import com.techelevator.dao.FoodOrderDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.techelevator.dao.UserDao;
import com.techelevator.security.jwt.JWTFilter;
import com.techelevator.security.jwt.TokenProvider;

import java.util.List;

@RestController
@CrossOrigin
public class FoodOrderController {

    private FoodOrderDao foodOrderDao;


    private final String API_BASE_URL = "http://localhost:9000/";


    public FoodOrderController(FoodOrderDao foodOrderDao) {
        this.foodOrderDao = foodOrderDao;
    }

//    @RequestMapping(path = "/menu/pizzas", method = RequestMethod.GET)
//    public List<Food> getPizzas() {
//        try {
//            return foodOrderDao.getPizzas();
//        } catch (DaoException e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve pizzas", e);
//        }
//    }

    // MAY HAVE TO USE QUERY PARAMETERS INSTEAD
//    @RequestMapping(path = "/menu/pizzas/{id}", method = RequestMethod.GET)
//    public Food getPizza(@PathVariable int id) {
//        try {
//            return foodOrderDao.getPizza(id);
//        } catch (DaoException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza not found", e);
//        }
//    }

    @RequestMapping(path = "/menu/specialty_pizzas", method = RequestMethod.GET)
    public List<SpecialtyPizza> getSpecialtyPizzas() {
        try {
            return foodOrderDao.getSpecialtyPizzas();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve specialty pizzas", e);
        }
    }

    @RequestMapping(path = "/menu/specialty_pizzas/{id}", method = RequestMethod.GET)
    public SpecialtyPizza getSpecialtyPizza(@PathVariable int id) {
        try {
            return foodOrderDao.getSpecialtyPizza(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specialty pizza not found", e);
        }
    }

//    @RequestMapping(path = "/menu/sides", method = RequestMethod.GET)
//    public List<Food> getSides() {
//        try {
//            return foodOrderDao.getSides();
//        } catch (DaoException e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve sides", e);
//        }
//    }
//
//    @RequestMapping(path = "/menu/sides/{id}", method = RequestMethod.GET)
//    public Food getSide(@PathVariable int id) {
//        try {
//            return foodOrderDao.getSide(id);
//        } catch (DaoException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Side not found", e);
//        }
//    }


//    @RequestMapping(path = "/order/{id}", method = RequestMethod.GET)
//    public FoodOrder getOrder(@PathVariable int id) {
//
//        try {
//            return foodOrderDao.getOrder(id);
//        } catch (DaoException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", e);
//        }
//    }

//    @RequestMapping(path = "/order/{id}/status", method = RequestMethod.GET)
//    public boolean getOrderStatus(@PathVariable int id) {
//        try {
//            return foodOrderDao.getOrderStatus(id);
//        } catch (DaoException e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve order status", e);
//        }
//    }
}