const main = {
    init : function() {
        let _this = this;
        $('#btn-save').on('click', function() {
            _this.save();
        });
        $('#btn-update').on('click', function() {
           _this.update();
        });
        $('#btn-delete').on('click', function() {
            _this.delete();
        });
    },
    save : function() {
        let data = {
            title : $('#title').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/posts/v1',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 등록되었습니다');
            window.location.href='/';
        }).fail(function(err){
            alert(JSON.stringify(err));
        });
    },
    update : function() {
        let data = {
            id : $('#id').val(),
            title : $('#title').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'PUT',
            url: '/api/posts/v1',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 수정되었습니다');
            window.location.href='/';
        }).fail(function(err){
            alert(JSON.stringify(err));
        });
    },
    delete : function() {

        $.ajax({
            type: 'DELETE',
            url: '/api/posts/v1/' + $('#id').val() ,
            contentType: 'application/json; charset=UTF-8',
        }).done(function(){
            alert('글이 삭제되었습니다');
            window.location.href='/';
        }).fail(function(err){
            alert(JSON.stringify(err));
        });
    }
};

main.init();