use std::env::{args, Args};
use rand::Rng;
use commutil::add_one as addOne;

fn main(){
    println!("Hi, Rico@2022");
    let mut args: Args = args();
    println!("{:?}", args);
    
    let first: String = args.nth(1).unwrap();
    println!("{}", first);

    let my_variable = 0;
    const MY_CONSTANT: u8 = 1;
    static MY_STATIC: u8 = 99;

    let mut my_mutable_variable = 101;

    my_sayhi();
    println!("{}, {},{}",my_variable, MY_CONSTANT, MY_STATIC);

    my_mutable_variable += 20;
    println!("my_mutable_variable = {}", my_mutable_variable);

    let rand_number = rand::thread_rng().gen_range(1..101);
    // println!("Ho, {} + 1 = {}", rand_number, commutil::add_one(rand_number));
    println!("Ho, {} + 1 = {}", rand_number, addOne(rand_number));

        let s: &Option<String> = &Some("hello".to_string());

    match s {
        Some(s) => println!("s is {}", s),
        _=> (),
    };

    let mut v_key_words = vec![]; // vec!  macro
    v_key_words.push("cai");
    v_key_words.push("23");
    println!("{:#?}",v_key_words);  // 使用 {:?} 或 {:#?}（后者更易读一些） 在 println! 中输出。
    assert_eq!(v_key_words[1], 23.to_string());
    let v2 = vec![5;10];
    println!("number is {}", v2[9]);
    
}

fn my_sayhi() -> u8 {
    return 88;    
}

