package io.spring.learning;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/ingredients")
@RequiredArgsConstructor
public class ManageIngredientsController {

    private final IngredientService ingredientService;

    @GetMapping
    public String ingredientsAdmin(Model model){
        model.addAttribute("ingredients", ingredientService.findAll());
        return "admin/ingredients";
    }

    @PostMapping
    public String addIngredient(Ingredient ingredient){
        ingredientService.addIngredient(ingredient);
        return "redirect:/admin/ingredients";
    }
}
