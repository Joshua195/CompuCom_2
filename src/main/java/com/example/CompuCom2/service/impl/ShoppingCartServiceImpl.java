package com.example.CompuCom2.service.impl;

import com.example.CompuCom2.converter.ShoppingCartConverter;
import com.example.CompuCom2.entity.ShoppingCart;
import com.example.CompuCom2.model.ProductModel;
import com.example.CompuCom2.model.ShoppingCartModel;
import com.example.CompuCom2.repository.ShoppingCartRepository;
import com.example.CompuCom2.service.ShoppingCartService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("shoppingCartServiceImpl")
public class ShoppingCartServiceImpl implements ShoppingCartService{
    private static final Log LOG = LogFactory.getLog(ShoppingCartServiceImpl.class);

    @Autowired
    @Qualifier("shoppingCartConverter")
    private ShoppingCartConverter shoppingCartConverter;

    @Autowired
    @Qualifier("shoppingCartRepository")
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    @Qualifier("productServiceImpl")
    private ProductServiceImpl productService;

    @Override
    public ArrayList<ShoppingCartModel> findAllProductsByUser(int id) {
        ArrayList<ShoppingCart> SC = (ArrayList<ShoppingCart>) shoppingCartRepository.findAllByIdUser(id);
        ArrayList<ShoppingCartModel> SCModels = new ArrayList<>();
        for (ShoppingCart sc : SC) {
            ProductModel productModel = productService.getProductById(sc.getIdProd());
//            Due to the idProd is not linked by JPSQL, We remove manually the id_products from his/her SC
            if (productModel == null){
                removeProductFromSC(id, sc.getIdProd());
            }else{
//                We added the SCModel and the ProductModel to the ArrayList<ShoppingCartModel>
                ShoppingCartModel shoppingCartModel = shoppingCartConverter.entityToModel(sc);
                shoppingCartModel.setProduct(productModel);
                SCModels.add(shoppingCartModel);
            }
        }
        return SCModels;
    }

    @Override
    public boolean addProductToSC(int id_user, int id_prod) {
        ShoppingCart shoppingCart = findByUserAndProduct(id_user, id_prod);

        if (shoppingCart == null){
            // The product for this user doesn't exists in his/her shopping cart
            ShoppingCart SC1 = shoppingCartRepository.save(new ShoppingCart(id_user, id_prod, 1));
            return SC1 != null;
        }else{
            // The product exists, then We only modify the quantity:
            shoppingCart.setQuantity((shoppingCart.getQuantity()+1));
            ShoppingCart SC1 = shoppingCartRepository.save(shoppingCart);
            return SC1 != null;
        }
    }


    @Override
    public int removeProductFromSC(int id_user, int id_prod) {
        LOG.info("METHOD: removeProductFromSC() --PARAM: id_user="+id_user+", id_prod="+id_prod);
        return shoppingCartRepository.removeByIdUserAndIdProd(id_user, id_prod);
    }

    @Override
    public ShoppingCart findByUserAndProduct(int id_user, int id_prod) {
        return shoppingCartRepository.findByIdUserAndIdProd(id_user, id_prod);
    }

    @Override
    public int numberOfProducts(int id_user) {
        ArrayList<ShoppingCart> cart = (ArrayList<ShoppingCart>) shoppingCartRepository.findAllByIdUser(id_user);
        int number = 0;
        for (ShoppingCart sc : cart) {
            number += sc.getQuantity();
        }
        return number;
    }

    @Override
    public ShoppingCart modifyQuantity(int id_sc, int quantity) {
        ShoppingCart sc = shoppingCartRepository.findByIdSc(id_sc);
        sc.setQuantity(quantity);
        sc = shoppingCartRepository.save(sc);
        return sc;
    }
}
