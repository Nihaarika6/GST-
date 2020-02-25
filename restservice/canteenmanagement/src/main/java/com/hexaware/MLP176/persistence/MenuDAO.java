package com.hexaware.MLP176.persistence;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import com.hexaware.MLP176.model.Menu;
/**
 * MenuDAO class used to fetch data from data base.
 * @author hexware
 */
public interface MenuDAO {
    /**
     * @return the all the Menu record.
     */
  @SqlQuery("Select * from Menu")
    @Mapper(MenuMapper.class)
    List<Menu> show();
    /**
    * @param menId fro reading menu Item Id.
    @return the selected record.
    */
  @SqlQuery("Select * from MENU WHERE MEN_ID =:menId")
  @Mapper(MenuMapper.class)
  Menu findByMenuId(@Bind("menId") int menId);
}
