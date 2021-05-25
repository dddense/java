package ru.itis.mimimeter.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.mimimeter.impl.models.Cat;

import java.util.List;

public interface CatsRepository extends JpaRepository<Cat, Long> {

    @Query(nativeQuery = true,
            value = "select id, account_id, name, image_path, count(*) " +
                    "from (" +
                    "         select cat_id as id, cat.account_id as account_id, name, image_path " +
                    "         from users_cats" +
                    "                  inner join cat cat on users_cats.cat_id = cat.id" +
                    "     ) res " +
                    "group by res.id, res.account_id, res.name, res.image_path " +
                    "order by count(*) desc " +
                    "limit 10;")
    List<Cat> findTop10ByVotes();
}