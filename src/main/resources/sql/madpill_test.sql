-- ----------------------------
-- Records of mp_drug
-- ----------------------------
INSERT INTO `mp_drug` (`id`, `name`, `produced_date`, `expire_date`, `description`, `reminders`,
                       `indication`, `contraindication`, `user_id`, `group_id`)
VALUES (2, '测试奥硝唑片postman', '2020-02-12', '2020-02-19', 'hello', NULL,
        '{\"content\":\"1.治疗原虫感染-毛滴虫感染（泌尿生殖感染）阿米巴原虫感染（肠、肝阿米巴虫病、阿米巴痢疾、阿米巴脓肿），贾第鞭毛虫病；2.治疗厌氧菌感染（如败血症脑膜炎、腹膜炎、手术后伤口感染、产后脓毒病、脓毒性流产、子宫内膜炎以及敏感菌引起的其它感染），预防各种手术后厌氧菌感染\"}',
        '{\"content\":\"对硝基咪唑类药物过敏的患者对此药也过敏，禁用于对此药过敏的患者；也禁用于脑和脊髓发生病原菌变的患者，羊癫疯及各种器官硬化症患者。\"}', 12, 0),
       (3, '测试奥硝唑片postman', '2020-02-12', '2020-02-19', 'hello', NULL,
        '{\"content\":\"1.治疗原虫感染-毛滴虫感染（泌尿生殖感染）阿米巴原虫感染（肠、肝阿米巴虫病、阿米巴痢疾、阿米巴脓肿），贾第鞭毛虫病；2.治疗厌氧菌感染（如败血症脑膜炎、腹膜炎、手术后伤口感染、产后脓毒病、脓毒性流产、子宫内膜炎以及敏感菌引起的其它感染），预防各种手术后厌氧菌感染\"}',
        '{\"content\":\"对硝基咪唑类药物过敏的患者对此药也过敏，禁用于对此药过敏的患者；也禁用于脑和脊髓发生病原菌变的患者，羊癫疯及各种器官硬化症患者。\"}', 14, 3),
       (4, '测试奥硝唑片postman', '2020-02-12', '2020-02-19', 'hello', NULL,
        '{\"content\":\"1.治疗原虫感染-毛滴虫感染（泌尿生殖感染）阿米巴原虫感染（肠、肝阿米巴虫病、阿米巴痢疾、阿米巴脓肿），贾第鞭毛虫病；2.治疗厌氧菌感染（如败血症脑膜炎、腹膜炎、手术后伤口感染、产后脓毒病、脓毒性流产、子宫内膜炎以及敏感菌引起的其它感染），预防各种手术后厌氧菌感染\"}',
        '{\"content\":\"对硝基咪唑类药物过敏的患者对此药也过敏，禁用于对此药过敏的患者；也禁用于脑和脊髓发生病原菌变的患者，羊癫疯及各种器官硬化症患者。\"}', 14, 3),
       (100, '奥硝唑片postman2', '2020-02-12', '2020-02-19', 'hello', NULL,
        '{\"content\":\"1.治疗原虫感染-毛滴虫感染（泌尿生殖感染）阿米巴原虫感染（肠、肝阿米巴虫病、阿米巴痢疾、阿米巴脓肿），贾第鞭毛虫病；2.治疗厌氧菌感染（如败血症脑膜炎、腹膜炎、手术后伤口感染、产后脓毒病、脓毒性流产、子宫内膜炎以及敏感菌引起的其它感染），预防各种手术后厌氧菌感染。\"}',
        '{\"content\":\"对硝基咪唑类药物过敏的患者对此药也过敏，禁用于对此药过敏的患者；也禁用于脑和脊髓发生病原菌变的患者，羊癫疯及各种器官硬化症患者。\"}', 14, 2),
       (119, '奥硝唑片', '2019-02-18', '2020-09-01', NULL, NULL,
        '{\"content\":\"1.治疗原虫感染-毛滴虫感染（泌尿生殖感染）阿米巴原虫感染（肠、肝阿米巴虫病、阿米巴痢疾、阿米巴脓肿），贾第鞭毛虫病；2.治疗厌氧菌感染（如败血症脑膜炎、腹膜炎、手术后伤口感染、产后脓毒病、脓毒性流产、子宫内膜炎以及敏感菌引起的其它感染），预防各种手术后厌氧菌感染。nkk\"}',
        '{\"content\":\"对硝基咪唑类药物过敏的患者对此药也过敏，禁用于对此药过敏的患者；也禁用于脑和脊髓发生病原菌变的患者，羊癫疯及各种器官硬化症患者。npp\"}', 12, 1);

-- ----------------------------
-- Records of mp_drug_tag
-- ----------------------------
INSERT INTO `mp_drug_tag` (`drug_id`, `tag_id`)
VALUES (100, 1000);

-- ----------------------------
-- Records of mp_group
-- ----------------------------
INSERT INTO `mp_group` (`id`, `name`, `create_at`, `create_by`, `can_delete`)
VALUES (1, '测试群组1', '2020-02-21 19:02:14', 12, false),
       (2, '测试群组2', '2020-02-21 19:10:30', 12, false),
       (3, '我的药箱', '2020-02-21 19:10:30', 14, true),
       (4, '测试群组1', '2020-02-21 19:10:30', 14, true),
       (5, '测试群组2', '2020-02-21 19:10:30', 14, true);

-- ----------------------------
-- Records of mp_tag
-- ----------------------------
INSERT INTO `mp_tag` (`id`, `name`, `user_id`)
VALUES (101, '头疼', 10086),
       (1000, 'shenmiu_delete_drugs', 14),
       (1001, 'shenmiu_delete_drugs', 14),
       (1002, '测试标签 1', 12),
       (1003, '测试标签 2', 12),
       (1004, '测试标签 3', 12),
       (1005, '测试标签 4', 12);

-- ----------------------------
-- Records of mp_user
-- ----------------------------
INSERT INTO `mp_user` (`id`, `open_id`, `created_at`)
VALUES (12, 'o9ik85FW6IYCSR1mXObaTdcb3yCU', '2020-02-18 13:54:29'),
       (14, 'o9ik85GiMt9_DDlc5lPNWbmnBAls', '2020-02-19 16:50:28');

-- ----------------------------
-- Records of mp_user_group
-- ----------------------------
BEGIN;
INSERT INTO `mp_user_group`
VALUES (12, 1, '办公室'),
       (12, 2, '老家'),
       (14, 3, '我的药箱'),
       (14, 4, '测试群组 1'),
       (14, 5, '测试群组 2');
COMMIT;

-- ----------------------------
-- Records of mp_warehouse
-- ----------------------------
-- BEGIN;
-- INSERT INTO `mp_warehouse` (`name`, `ingredient`, `character`, `indication`, `specification`, `usage`, `adverse_effect`, `contraindication`, `warning`, `storage`, `indate`)
-- VALUES ('左旋炔诺孕酮硅胶棒', '左旋甲基炔诺酮', '乳白色圆柱状棒，具有弹性，内装白色粉末。', '育龄妇女，要求长期避孕者。', '（1）36mg（2）75mg', '于月经来潮的1～5天，局麻下在上臂或股内侧作一长度为2～3mm的横切口后，用埋植针将药棒呈扇形植入皮下，每人一次6支，伤口贴以“创可贴”后，纱布包扎即可。', '主要表现为月经紊乱（月经过频、经期延长、月经稀发、闭经或点滴出血等）及类早孕反应（恶心、头晕、乏力、嗜睡等），乳房胀痛，偶见体重增加、血压上升、痤疮、精神抑郁或性欲改变等，个别埋植局部发生感染。', '急慢性肝病、肾炎、肿瘤、糖尿病、甲亢、严重高血压、血栓性疾病、镰状细胞贫血及原因不明的阴道流血者、癫痫、可疑妊娠者和应用抗凝血药者禁用。', '1.既往月经不调、经常有闭经史者、产后或流产后尚未恢复正常月经者、哺乳期或45岁以上妇女不宜使用。2.如出现不能耐受的不良反应，可由医生对症治疗，必要时可取出药棒。3.如妇女规则使用巴比妥类药物、苯妥英钠、解热止痛、保泰松、利福平和四环素等药物，可影响避孕效果。4.计划妊娠者，需在取出六个月后方可受孕。5.应在县级医院或计划生育指导站以上的医疗单位进行植入、观察和取出。6.手术操作人员必须经严格的技术培训取得资格后方能开展此项手术。7.植入的妇女应定期到上述医疗单位进行随访观察。8.埋植期间，如植入者发生妊娠，建议人工流产终止妊娠，并取出埋植物。9.育龄妇女在使用时，应征得本人同意。', '遮光，密闭保存。', '暂定36个月。');
-- INSERT INTO `mp_warehouse` (`name`, `indication`)
-- VALUES ('左氧氟沙星葡萄糖注射液', '主治呼吸系统感染，泌尿系统感染，生殖系统感染，皮肤软组织感染，肠道感染，败血症等');
-- INSERT INTO `mp_warehouse` (`name`, `ingredient`, `character`, `indication`, `usage`, `adverse_effect`, `contraindication`, `warning`, `pregnant`, `children`, `old`, `interaction`)
-- VALUES ('佐米曲普坦片', '成份和结构：本品主要成分为佐米曲普坦，其化学名称为：（S）-4-[3-[2-（二甲胺基）乙基]-1H-吲哚-5-基-甲基]-2-噁唑烷酮', '本品为薄膜衣片，除去包衣后显白色或类白色。', '适用于成人伴或不伴先兆症状的偏头痛的急性治疗。', '治疗偏头痛发作的推荐剂量为2.5mg（一片）。如果24小时内症状持续或复发，再次服药仍有效。如需二次服药，时间应与首次服药时间最少相隔2小时。服用本品2.5mg（一片），头痛减轻不满意者，在随后的发作中，可服用5mg（二片）。通常服药1小时内效果最明显。偏头痛发作期间无论何时服用本药。都同样有效，建议发病后尽早服用。反复发作时，建议24小时内服用总量不超过15mg（六片）。本品不作为偏头痛的预防性药物。肾损害患者使用本品无需调整剂量。', '本药耐受性好。不良反应很轻微/缓和、短暂，且不需治疗亦能自行缓解。可能的不良反应多出现在服药后4小时，继续用药未见增多。最常见的不良反应包括：偶见恶心、头晕、嗜睡、温热感、无力、口干。感觉异常或感觉障碍已见报道。咽喉部、颈部、四肢及胸部可能出现沉重感、紧缩感和压迫感（心电图上没有缺血改变的证据），还可出现肌痛、肌肉无力。', '禁用于对本品任何成份过敏的患者。血压未经控制的病人不应使用。', '本药仅应用于已诊断明确的偏头痛患者。要注意排除其它严重潜在性神经科疾病。尚无偏瘫性或基底动脉性偏头痛患者使用本品的资料，不推荐使用。症状性帕金森氏综合症或患者与其它心脏旁路传导有关的心律失常者不应使用本品。此类化合物（5HTID激动剂）与冠状动脉的痉挛有关，因此，临床试验中未包括缺血性心脏病患者，故此类患者不推荐使用本品。由于还可能存在一些未被识别的冠状动脉疾病患者，所以建议开始使用5HTID激动剂，治疗前先做心血管的检查。与使用其他5—HTID激动剂类似，服用佐米曲普坦后，心前区可出现非典型心绞痛的感觉；但是临床试验中，此类症状与心律失常或心电图上显示的缺血改变无关。目前尚无肝损害者使用本品的临床或药代动力学的经验，不推荐使用。对驾驶及机械操纵能力无明显损害，本药20mg时，患者在精神运动测试中，操纵项目未见明显的损害。使用本品不会损害患者驾驶及机械操纵的能力，但仍要考虑到本药可能引起嗜睡。', '目前还没有妊娠妇女使用本品的研究。应该只有在对母亲的益处大于对胎儿的潜在性危险的情况下，才考虑使用本药。哺乳动物试验显示佐米曲普坦可进入乳汁，尚无人类使用的资料。因此，哺乳期妇女慎用。', '本药用于儿童患者的安全性和有效性尚未确定。', '本药用于65岁以上患者的安全性和有效性尚未确定。', '1、没有证据表明，使用偏头痛预防性药物（例如β—受体阻滞剂、口服二氢麦角胺、苯噻啶）对本药的疗效有任何影响。急性对症治疗，如使用扑热息痛、灭吐灵及麦角胺不影响本品的药代动力学及耐受力。2、在健康个体中，没有见到本品与麦角胺有药代动力学的相互作用。本药与麦角胺/咖啡因合用耐受性良好，与单独应用本药相比，不良反应没有增加，血压也无改变。3、使用本药治疗12小时内应避免使用其他5HTID激动剂。使用吗氯贝胺（一种特殊的单胺氧化酶—A抑制剂）后，佐米曲普坦的曲线下面积有少量增加（26%），活性代谢物的曲线下面积有三倍增加。因而对于使用单胺氧化酶—A抑制剂的患者，建议24小时内服用本品的最大量为7.5mg。4、司来吉兰（一种单胺氧化酶—β抑制剂）和氯西汀（一种选择性5—羟色胺再摄取抑制剂）对佐米曲普坦的药代动力学参数没有影响。健康老人中，佐米曲普坦的药代动力学与健康青年志愿者是相似的。5、与西咪替丁、口服避孕药合用时，也可使本品的血药浓度增加。与心得安合用可延缓本品的代谢。');
-- INSERT INTO `mp_warehouse` (`name`, `ingredient`, `character`, `indication`, `usage`, `adverse_effect`, `contraindication`, `warning`, `pregnant`, `children`, `interaction`, `overdose`, `storage`)
-- VALUES ('佐匹克隆胶囊', '本品主要成份为：佐匹克隆', '本品为胶囊剂，内容物为白色或类白色颗粒。', '用于各种失眠症。', '口服，1片，临睡时服；老年人最初临睡时服半片，必要时1片；肝功能不全者，服半片为宜。', '与剂量及患者的敏感性有关。偶见思睡、口苦、口干、肌无力、遗忘、醉态，有些人出现异常的易恐、好斗、易受刺激或精神错乱、头痛、乏力。长期服药后突然停药会出现戒断症状（因药物半衰期短故出现较快），可能有较轻的激动、焦虑、肌痛、震颤、反跳性失眠及恶梦、恶心及呕吐,罕见较重的痉挛、肌肉颤抖、神志模糊（往往继发于较轻的症状）。', '禁用于对本品过敏者，失代偿的呼吸功能不全患者，重症肌无力、重症睡眠呼吸暂停综合征患者。', '（1）肌无力患者用药时需注意医疗监护，呼吸功能不全者和肝、肾功能不全者应适当调整剂量。（2）使用本品时应绝对禁止摄入酒精饮料。（3）连续用药时间不宜过长，突然停药可引起停药综合征应谨慎，服药后不宜操作机械及驾车。', '孕期妇女慎用。因本品在乳汁中浓度高，授乳期妇女不宜使用。', '15岁以下儿童不宜使用本品。', '（1）与神经肌肉阻滞药（筒箭毒，肌松药）或其他中枢神经抑制药同服可增强镇静作用。（2）与苯二氮卓类抗焦虑药和催眠药同服，戒断综合征的出现可增加。', '服用过量的药物可出现熟睡甚至昏迷，应对症治疗。', '遮光，密封保存。');
-- COMMIT;