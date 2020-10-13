# docker start arangodb

```bash
docker run -e ARANGO_ROOT_PASSWORD=root123 -p 18529:8529 --name arango -d arangodb/arangodb:3.7.2
```

# run test

run insert() first and then run queryEdge()

```java
package com.example.demo;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.core.ArangoOperations;
import com.example.demo.model.DocumentModel;
import com.example.demo.model.EdgeModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author korov
 * @date 2020/10/13
 */
public class Test extends DemoApplicationTests {
    @Autowired
    private ArangoOperations arangoOperations;

    @org.junit.jupiter.api.Test
    public void insert() {
        for (int i = 0; i < 5000; i++) {
            String uuid = UUID.randomUUID().toString();
            DocumentModel fromDoc = new DocumentModel();
            fromDoc.setUuid(uuid);
            arangoOperations.insert(fromDoc);

            uuid = UUID.randomUUID().toString();
            DocumentModel toDoc = new DocumentModel();
            toDoc.setUuid(uuid);
            arangoOperations.insert(toDoc);

            EdgeModel edgeModel = new EdgeModel();
            edgeModel.setFrom(fromDoc);
            edgeModel.setTo(toDoc);
            arangoOperations.insert(edgeModel);
        }
    }

    /**
     * cursor not found exception
     */
    @org.junit.jupiter.api.Test
    public void queryEdge() throws InterruptedException {
        String query = "FOR docFrom IN documentModel\n" +
                "For docTo,edge,edges IN 1..1 ANY docFrom edgeModel\n" +
                "RETURN edge";
        ArangoCursor<EdgeModel> edgeArangoCursor = arangoOperations.query(query, null, null, EdgeModel.class);
        int count = 0;
        while (edgeArangoCursor.hasNext()) {
            EdgeModel edge = edgeArangoCursor.next();
            Thread.sleep(100);
            System.out.println(count++);
        }
    }
}
```

