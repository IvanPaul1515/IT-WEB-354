 public ResponseEntity<String> consulta(@PathVariable() String name, @PathVariable() String codigo){
        System.out.println("ENtra a esta parte por lo menos...");
       Mockup omock = mockS.buscarCodigoAndNombre(name,codigo);
        DateTimeFormatter nt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
       System.out.println(LocalDateTime.now().format(nt)+" otra: "+omock.getExp().format(nt));
       if((LocalDateTime.now()).isBefore(omock.getExp())){
            MediaType temp =null;
            if(omock.getContent().equals("text/plain")) {
                temp =MediaType.TEXT_PLAIN;
            }else if(omock.getContent().equals("text/html")) {
                temp=MediaType.TEXT_HTML;
            }else if(omock.getContent().equals("application/json")) {
                temp = MediaType.APPLICATION_JSON;
            }else if(omock.getContent().equals("application/xml")) {
                temp = MediaType.APPLICATION_XML;
            }

            HttpHeaders headers = new HttpHeaders();
            if(!omock.getHeaders().isEmpty()) {
                JSONObject headersJson = new JSONObject(omock.getHeaders());
                for (String key : headersJson.keySet()) {
                    headers.add(key, headersJson.getString(key));
                }
            }

            try {
                Thread.sleep(omock.getDemora()*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new JSONObject(omock.getBody()));
            return ResponseEntity.status(Integer.parseInt(omock.getStatus())).contentType(temp).headers(headers).body(omock.getBody());
       }else{
           HttpHeaders headers = new HttpHeaders();
           headers.add("Location", "/");
           return  new ResponseEntity<>(headers, HttpStatus.FOUND);
       }
    }
