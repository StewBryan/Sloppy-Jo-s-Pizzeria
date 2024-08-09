package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Food;
import com.techelevator.model.Item;
import com.techelevator.model.SpecialtyPizza;
import com.techelevator.model.Topping;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcFoodOrderDao implements FoodOrderDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcFoodOrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SpecialtyPizza> getSpecialtyPizzas() {
        List<SpecialtyPizza> specialtyPizzas = new ArrayList<>();

        String sql = "SELECT specialty_pizza_id, name, base_price FROM specialty_pizza;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                specialtyPizzas.add(mapRowToSpecialtyPizza(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return specialtyPizzas;
    }

    public SpecialtyPizza getSpecialtyPizza(int id) {
        SpecialtyPizza specialtyPizza = null; // Initialize as null
        String sql = "SELECT * FROM specialty_pizza WHERE specialty_pizza_id = ?;";
        try {
            // Execute the query and fetch the results
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) { // Check if a result was returned
                specialtyPizza = mapRowToSpecialtyPizza(results); // Map the row to SpecialtyPizza
            }
            return specialtyPizza; // Return the specialty pizza or null if not found
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e); // Catch other DB-related exceptions
        }
    }




    @Override
    public void updatePizza(Food pizza) {

    }

    @Override
    public void deletePizza(int id) {

    }

//    public Item addPizza(pizza) {
//        Item newItem = new Item();
//        String sql = "INSERT INTO item (sauce_id, topping_id, crust_id)" +
//                "VALUES(?, ?, ?)" +
//                "RETURNING item_id";
//        try {
//            int newPizzaId = jdbcTemplate.queryForObject(sql, int.class);
//            newItem = getPizzaById();
//        } catch (Exception e) {
//            throw new DaoException(e.getMessage());
//        }
//
//        return newItem;
//    }

    @Override
    public Item getPizzaById(int id) {
        Item customPizza = null;
        String sql = "SELECT * FROM item WHERE item_id = ?;";

        try {
            // Execute the query and fetch the results
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) { // Check if a result was returned
                customPizza = mapRowToCustomPizza(results); // Map the row to SpecialtyPizza
            }
            return customPizza; // Return the specialty pizza or null if not found
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e); // Catch other DB-related exceptions
        }
    }

    @Override
    public void addPizza(Item pizza) {
        String sql = "INSERT INTO item (sauce_name, crust_name, size_name) VALUES (?, ?, ?)";

        if (pizza.getSauce() == null) {
            throw new DaoException("Sauce cannot be null");
        }
        if (pizza.getCrust() == null) {
            throw new DaoException("Crust cannot be null");
        }
        if (pizza.getDiameter() == null) {
            throw new DaoException("Diameter cannot be null");
        }

        try {
            jdbcTemplate.update(sql, pizza.getSauce(), pizza.getCrust(), pizza.getDiameter());
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e);
        }
    }

    private Item mapRowToCustomPizza(SqlRowSet rowSet) {
        Item customPizza = new Item();
        customPizza.setItemId(rowSet.getInt("item_id"));
        customPizza.setSauce(rowSet.getString("sauce_name"));
        customPizza.setCrust(rowSet.getString("crust_name"));
        customPizza.setDiameter(rowSet.getString("size_name"));
        return customPizza;
    }

//    public List<Topping> addToppings(Item pizza) {
//        if (pizza == null) {
//            throw new DaoException("Pizza object cannot be null");
//        }
//        // Assuming getToppings() returns an array or list of Topping objects
//        List<Topping> toppings = pizza.getToppings();
//        if (toppings != null || toppings.size() > 0) {
//            throw new DaoException("Topping array cannot be null or empty");
//        }
//
//        List<Topping> updatedToppings = new ArrayList<>();
//        String insertSql = "INSERT INTO item_topping (pizza_id, topping_id) VALUES (?, ?)";
//
//        try {
//            for (Topping topping : toppings) {
//                // Insert the topping and associate it with the pizza's ID
//                jdbcTemplate.update(insertSql, pizza.getItemId(), topping.getTopping_id()); // Make sure this matches your Topping class
//                updatedToppings.add(topping);
//            }
//
//            // Retrieve and return the updated list of toppings for the pizza
//            return getToppings(pizza);
//        } catch (DataAccessException e) {
//            throw new DaoException("Database access error", e);
//        }
//    }
//
//    public List<Topping> getToppings(Item pizza) {
//        String sql = "SELECT t.id, t.name FROM item_topping it " +
//                "JOIN topping t ON it.topping_id = t.id " +
//                "WHERE it.pizza_id = ?"; // Assuming pizza_id is the foreign key
//
//        List<Topping> toppings = jdbcTemplate.query(sql, new Object[]{pizza.getItemId()},
//                (rs, rowNum) -> {
//                    Topping topping = new Topping();
//                    topping.setTopping_id(rs.getInt("id"));
//                    topping.setName(rs.getString("name"));
//                    return topping;
//                });
//
//        return toppings; // Convert List to array
//    }
//
//    public SpecialtyPizza addSpecialtyPizza(int id) {
//        SpecialtyPizza special = null;
//        String sql = "SELECT * FROM specialty_pizza WHERE specialty_pizza_id = ? ";
//        try {
//            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
//            return mapRowToSpecialtyPizza(results);
//        } catch (DataAccessException e) {
//            throw new DaoException("Database access error", e); // Wrap DataAccessException
//        }
//    }
//
//


    private SpecialtyPizza mapRowToSpecialtyPizza(SqlRowSet rowSet) {
        SpecialtyPizza specialtyPizza = new SpecialtyPizza();
        specialtyPizza.setId(rowSet.getInt("specialty_pizza_id"));
        specialtyPizza.setName(rowSet.getString("name"));
        specialtyPizza.setPrice(rowSet.getDouble("base_price"));
        return specialtyPizza;
    }




}
